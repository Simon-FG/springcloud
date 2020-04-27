package com.doubleskyline.manage.modules.bd.controller;


import com.alibaba.fastjson.JSON;
import com.doubleskyline.core.config.mvc.MultiRequestBody;
import com.doubleskyline.core.controller.BaseController;
import com.doubleskyline.core.model.PageParam;
import com.doubleskyline.core.model.PageResult;
import com.doubleskyline.core.model.R;
import com.doubleskyline.core.util.StringUtils;
import com.doubleskyline.manage.constant.MqConstant;
import com.doubleskyline.manage.modules.bd.condition.SimplePageParam;
import com.doubleskyline.manage.modules.bd.condition.SmsCondition;
import com.doubleskyline.manage.modules.bd.entity.SmsEntity;
import com.doubleskyline.manage.modules.bd.service.*;
import com.doubleskyline.manage.modules.bd.vo.LocVO;
import com.doubleskyline.manage.modules.bd.vo.SmsVO;
import com.doubleskyline.manage.util.TimeUtil;
import com.doubleskyline.manage.util.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.*;

/**
 * 北斗报文
 *
 * @author ZZY
 * @date 2020-02-21 11:38:26
 */
@Api(tags = "北斗报文")
@RestController
@RequestMapping("/")
@Slf4j
public class SmsController extends BaseController {

    @Autowired
    private SmsService smsService;
    @Autowired
    private SosService sosService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private FeignService feignService;
    @Autowired
    private LogService logService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

//    @Autowired
//    private  RedisTemplate<String,String> redisTemplate;

    @Autowired
    private  TokenUtil tokenUtil;

    /**
     * 北斗报文查询接口
     */
    @ApiOperation("北斗报文查询接口")
    @GetMapping("/v1/querybdmsg")
    public R queryBdMsg(@MultiRequestBody(required = false) SimplePageParam simplePageParam, @MultiRequestBody(required = false) SmsCondition smsCondition,String token) {

        if(StringUtils.isBlank(token)){
            return R.error("token 不能为空");
        }

//        // *号 必须要加，否则无法模糊查询
//        String prefix = "backend-boot_shiro_session_"+"*";
//        // 获取所有的key
//        Set<String> keys = redisTemplate.keys(prefix);
//
//        if(keys.size() != 0) {
//            for (String key : keys) {
//                if (key.length() > prefix.length()) {
//                    String substring = key.substring(key.lastIndexOf("_") + 1, key.length());
//                    if(!token.equals(substring))
//                    return R.error("用户未登录");
//                }
//            }
//        }else {
//            return R.error("用户未登录");
//        }

        if(!tokenUtil.isToken(token)){
            return R.error("用户未登录");
        }

        if(smsCondition.getDestBdId() == null){
            return R.error("destBdId 不能为空");
        }
        if(smsCondition.getStartTime() == null){
            return R.error("startTime 不能为空");
        }
        if(smsCondition.getEndTime() == null){
            return R.error("endTime 不能为空");
        }

        if(logger.isDebugEnabled()){
            logger.debug(String.format("参数 --> pageParam:{},sms:{}", simplePageParam, smsCondition));
        }

        PageParam pageParam = new PageParam();
        if(null != simplePageParam){
            pageParam.setPageSize(simplePageParam.getPageSize());
            pageParam.setPageNum(simplePageParam.getPageNum());
        }

        SmsEntity smsEntity = new SmsEntity();
        if(null != smsCondition) {
            smsEntity.setSendCardId(smsCondition.getSendBdId());
            smsEntity.setToCardId(smsCondition.getDestBdId());
            smsEntity.setStartTime(smsCondition.getStartTime());
            smsEntity.setEndTime(smsCondition.getEndTime());
        }
        PageResult<SmsEntity> page = smsService.page(pageParam, smsEntity);

        //保存日志信息
        logService.logSave();

        return R.ok().result(page);
    }

    /**
     * 北斗报文发送接口
     */
    @ApiOperation("北斗报文发送接口")
    @PostMapping("/v1/sendbdmsg")
    public R sendBdMsg( SmsEntity sms,String token) {

        try {
            if(sms.getContent().getBytes("UTF-8").length > 77){
                return R.error("信息内容过长，建议在25个汉字内");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if(StringUtils.isBlank(token)){
            return R.error("token 不能为空");
        }

        if(!tokenUtil.isToken(token)){
            return R.error("用户未登录");
        }

        if(sms.getToCardId()==null){
            return R.error("toCardId 不能为空");
        }
        if(sms.getContent()==null){
            return R.error("content 不能为空");
        }

        if(logger.isDebugEnabled()){
            logger.debug(String.format("参数 --> sms:{}", sms));
        }

        sms.setMt(0);
        sms.setStatus("2");
        //因为调用发送接口，只能是指挥机。设置发送卡id为指挥机 0
        sms.setSendCardId("0");
        sms.setType("1");
        sms.setDelFlag("0");
        sms.setCreateTime(new Date());
        //发送消息入库
        smsService.insertSms(sms);

//      将消息发送到MQ
        Map<String, Object> mqMap = new HashMap<>();
        mqMap.put("MT", sms.getMt());
        mqMap.put("SC", sms.getSendCardId() == null ? null : Integer.valueOf(sms.getSendCardId()));
        mqMap.put("DC", sms.getToCardId() == null ? null : Integer.valueOf(sms.getToCardId()));
        mqMap.put("ST", sms.getCreateTime() == null ? null : sms.getCreateTime().toString());
        mqMap.put("MC", sms.getContent());
        mqMap.put("ID", sms.getId());
        rabbitTemplate.convertAndSend(MqConstant.BD_MessageDown_EXCHANGE , MqConstant.BD_MessageDown_KEY, JSON.toJSON(mqMap));

        //保存日志信息
        logService.logSave();

        return R.ok();
    }


    /**
     * 平台北斗报文查询接口
     */
    @ApiOperation("平台北斗报文查询")
    @GetMapping("/v1/querylist")
    public R getList(@MultiRequestBody(required = false) SimplePageParam simplePageParam, @MultiRequestBody(required = false) SmsCondition smsCondition) {

        if(logger.isDebugEnabled()){
            logger.debug(String.format("参数 --> pageParam:{},sms:{}", simplePageParam, smsCondition));
        }

        PageParam pageParam = new PageParam();
        if(null != simplePageParam){
            pageParam.setPageSize(simplePageParam.getPageSize());
            pageParam.setPageNum(simplePageParam.getPageNum());
        }

        SmsEntity smsEntity = new SmsEntity();
        if(null != smsCondition) {
            smsEntity.setSendCardId(smsCondition.getSendBdId());
            smsEntity.setToCardId(smsCondition.getDestBdId());
            smsEntity.setStartTime(smsCondition.getStartTime());
            smsEntity.setEndTime(smsCondition.getEndTime());
            smsEntity.setNetworkType(smsCondition.getNetworkType());
        }
        PageResult<SmsEntity> page = smsService.pages(pageParam, smsEntity);

        return R.ok().result(page);
    }

    /**
     * 平台数据管理查询报文接口
     */
    @ApiOperation("平台数据管理查询报文查询")
    @GetMapping("/v1/querybdlist")
    public R getBdList( @MultiRequestBody(required = false) SmsCondition smsCondition) {
        if(logger.isDebugEnabled()){
            logger.debug(String.format("参数 --> sms:{}", smsCondition));
        }
        SmsEntity smsEntity = new SmsEntity();
        if(null != smsCondition) {
            smsEntity.setSendCardId(smsCondition.getSendBdId());
            smsEntity.setStartTime(smsCondition.getStartTime());
            smsEntity.setEndTime(smsCondition.getEndTime());
            smsEntity.setNetworkType(smsCondition.getNetworkType());
        }
        List<SmsVO> list = smsService.getSmsData(smsEntity);
        return R.ok().result(list);
    }

    /**
     * 监视平台查询最新五条通信
     * @return
     */
    @ApiOperation("监视平台查询最新五条通信")
    @GetMapping("/sms/v1/getlistnew5")
    public R getListNew5() {
        return R.ok().result(smsService.getListNew5());
    }

    /**
     * 监视平台运营统计
     * @return
     */
    @ApiOperation("监视平台运营统计")
    @GetMapping("/v1/getoperate")
    public R getOperate() {
        Map<String, Object> map = new HashMap<>();
        int smsCount = 0;
        int sosCount = 0;
        int locCount = 0;
        //查询报文 按类型分组
        List<SmsVO> smsCountList = smsService.getSmsCount();
        for (int i = 0; i < smsCountList.size(); i++) {
            //将各类型对应的个数相加
            smsCount += smsCountList.get(i).getCount();
            if( smsCountList.get(i).getType() == 1){
                smsCountList.get(i).setName("北斗");
            }else {
                smsCountList.get(i).setName("4G");
            }
        }
        //查询报警 按类型分组
        List<SmsVO> sosCountList = sosService.getSosCount();
        for (int i = 0; i < sosCountList.size(); i++) {
            //将各类型对应的个数相加
            sosCount += sosCountList.get(i).getCount();
            if( sosCountList.get(i).getType() == 1){
                sosCountList.get(i).setName("北斗");
            }else {
                sosCountList.get(i).setName("4G");
            }
        }
        //查询位置 按类型分组
        List<LocVO> locCountList = locationService.getLocCount();
        for (int i = 0; i < locCountList.size(); i++) {
            //将各类型对应的个数相加
            locCount += locCountList.get(i).getCount();
            if( locCountList.get(i).getType() == 1){
                locCountList.get(i).setName("北斗");
            }else {
                locCountList.get(i).setName("4G");
            }
        }

        map.put("smsCount",smsCount);
        map.put("sosCount",sosCount);
        map.put("locCount",locCount);
        map.put("sms",smsCountList);
        map.put("sos",sosCountList);
        map.put("loc",locCountList);
        return R.ok().result(map);
    }

    /**
     * 监视平台查询最近六个月的通信
     * @return
     */
    @ApiOperation("监视平台查询最近六个月的通信")
    @GetMapping("/sms/v1/getlistmonth")
    public R getListMonth() throws ParseException {
        //获取最近六个月的通信数量
        List<SmsVO> smsMonth = smsService.getSmsMonth(TimeUtil.getliuMonth());
        //获取最近六个月的时间集合
        List<String> timeList = TimeUtil.getliuMonth();
        //存最近六个月中哪个月有数据
        List<String> flag = new ArrayList<>();
        //将数据都存到这个集合中返回
        List<Object> list = new ArrayList<>();

        for (int i = 0; i < timeList.size(); i++) {
            for (int j = 0; j < smsMonth.size(); j++) {
                if(smsMonth.get(j).getCreateTime().equals(timeList.get(i))){
                    list.add(smsMonth.get(j));
                    flag.add(smsMonth.get(j).getCreateTime());
                }
            }
            //这个时间在是否存在数据
            if(!flag.contains(timeList.get(i))){
                SmsVO smsVO = new SmsVO();
                smsVO.setCount(0);
                smsVO.setCreateTime(timeList.get(i));
                list.add(smsVO);
            }
        }
        return R.ok().result(list);
    }

    /**
     * 终端地图统计信息
     * @return
     */
    @ApiOperation("终端地图统计信息")
    @RequestMapping("/v1/sms/getdatasum")
    public R getDataSum(){
        Map<String, Object> map = new HashMap<>();
        String[] cards = feignService.getCards();
        List<String> strings = Arrays.asList(cards);
        Integer locationNum = locationService.getCount(strings);
        Integer smsNum = smsService.getCount(strings);
        map.put("cardNum",cards.length);
        map.put("smsNum",smsNum);
        map.put("locationNum",locationNum);
        return R.ok().result(map);
    }

    /**
     *  监视平台北斗报文查询接口
     */
    @ApiOperation("监视平台北斗报文查询")
    @GetMapping("/v1/findlist")
    public R findList(@MultiRequestBody(required = false) SimplePageParam simplePageParam, @MultiRequestBody(required = false) SmsCondition smsCondition) {

        if(logger.isDebugEnabled()){
            logger.debug(String.format("参数 --> pageParam:{},sms:{}", simplePageParam, smsCondition));
        }

        PageParam pageParam = new PageParam();
        if(null != simplePageParam){
            pageParam.setPageSize(simplePageParam.getPageSize());
            pageParam.setPageNum(simplePageParam.getPageNum());
        }
        SmsEntity smsEntity = new SmsEntity();
        if(null != smsCondition) {
            smsEntity.setSendCardId(smsCondition.getSendBdId());
            smsEntity.setToCardId(smsCondition.getDestBdId());
            smsEntity.setStartTime(smsCondition.getStartTime());
            smsEntity.setEndTime(smsCondition.getEndTime());
        }
        PageResult<SmsEntity> page = smsService.pageFind(pageParam, smsEntity);

        return R.ok().result(page);
    }


}
