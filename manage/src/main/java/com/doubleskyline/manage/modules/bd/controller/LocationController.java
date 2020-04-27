package com.doubleskyline.manage.modules.bd.controller;

import com.doubleskyline.core.config.mvc.MultiRequestBody;
import com.doubleskyline.core.controller.BaseController;
import com.doubleskyline.core.model.PageParam;
import com.doubleskyline.core.model.PageResult;
import com.doubleskyline.core.model.R;
import com.doubleskyline.core.util.StringUtils;
import com.doubleskyline.manage.listener.ZhjMqListener;
import com.doubleskyline.manage.modules.bd.condition.LocationCondition;
import com.doubleskyline.manage.modules.bd.condition.SimplePageParam;
import com.doubleskyline.manage.modules.bd.condition.SmsCondition;
import com.doubleskyline.manage.modules.bd.entity.LocationEntity;
import com.doubleskyline.manage.modules.bd.entity.SmsEntity;
import com.doubleskyline.manage.modules.bd.service.FeignService;
import com.doubleskyline.manage.modules.bd.service.LocationService;
import com.doubleskyline.manage.modules.bd.service.LogService;
import com.doubleskyline.manage.modules.bd.vo.LocVO;
import com.doubleskyline.manage.modules.bd.vo.OnLineSumOut;
import com.doubleskyline.manage.util.TokenUtil;
import com.doubleskyline.manage.util.excel.ExportExcel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 北斗位置
 *
 * @author ZZY
 * @date 2020-02-21 11:38:26
 */
@Api(tags = "北斗位置")
@RestController
@RequestMapping("/")
@Slf4j
public class LocationController extends BaseController {

    @Autowired
    private LocationService locationService;
    @Autowired
    private LogService logService;
    @Autowired
    private FeignService feignService;
    @Autowired
    private TokenUtil tokenUtil;

    final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    /**
     * 查找一个终端的多个位置记录
     */
    @ApiOperation("查找一个终端的多个位置记录")
    @GetMapping("/v1/querybdlocation")
    public R queryBdLocation(@MultiRequestBody(required = false) SimplePageParam simplePageParam, @MultiRequestBody(required = false) LocationCondition locationCondition,String token) {

        if(StringUtils.isBlank(token)){
            return R.error("token 不能为空");
        }

        if(!tokenUtil.isToken(token)){
            return R.error("用户未登录");
        }

        if(locationCondition.getCardId() == null){
            return R.error("cardId 不能为空");
        }

        if(locationCondition.getCardId().length() != 6 && !locationCondition.getCardId().toString().equals("0")){
            return R.error("cardId 格式不正确！！！卡应该为六位");
        }

        if(locationCondition.getStartTime() == null){
            return R.error("startTime 不能为空");
        }
        if(locationCondition.getEndTime() == null){
            return R.error("endTime 不能为空");
        }

        if(logger.isDebugEnabled()){
            logger.debug(String.format("参数 --> pageParam:{},location:{}", simplePageParam, locationCondition));
        }

        PageParam pageParam = new PageParam();
        if(null != simplePageParam) {
            pageParam.setPageNum(simplePageParam.getPageNum());
            pageParam.setPageSize(simplePageParam.getPageSize());
        }

        LocationEntity location = new LocationEntity();
        if(null != location) {
            location.setCardId(locationCondition.getCardId());
            location.setStartTime(locationCondition.getStartTime());
            location.setEndTime(locationCondition.getEndTime());
        }
        PageResult<LocationEntity> page = locationService.page(pageParam, location);
        //保存日志
        logService.logSave();
        return R.ok().result(page);
    }

    /**
     * 查找多个终端的最新位置记录
     */
    @ApiOperation("查找多个终端的最新位置记录")
    @GetMapping("/v1/queryrecentbdlocation")
    public R queryRecentBdLocation(String[] cardId,String token) {

        if(StringUtils.isBlank(token)){
            return R.error("token 不能为空");
        }

        if(!tokenUtil.isToken(token)){
            return R.error("用户未登录");
        }

        if(cardId == null){
            return R.error("cardId 不能为空");
        }

        for (int i = 0; i < cardId.length; i++) {
            if(cardId[i].length() != 6 && !cardId[i].toString().equals("0")){
                return R.error("cardId 格式不正确！！！每个卡应该为六位，用英文 , 隔开");
            }
        }

        if(logger.isDebugEnabled()){
            logger.debug(String.format("参数 --> cardId:{}", cardId));
        }
        List<LocationEntity> list = locationService.queryRecentBdLocation(cardId);
        //保存日志
        logService.logSave();
        return R.ok().result(list);
    }

    /**
     * 查询位置上报的设备是否在线  0:离线 1:在线  -1:不存在
     */
    @ApiOperation("查询位置上报的设备是否在线  0:离线 1:在线  -1:不存在")
    @PostMapping("/v1/isOnLine")
    public Map isOnLine (String cardId,String token) throws ParseException {

        if(StringUtils.isBlank(token)){
            Map<Object, Object> hashMap = new HashMap<>();
            hashMap.put("error","token 不能为空");
            return hashMap;
        }

        if(!tokenUtil.isToken(token)){
            Map<Object, Object> hashMap = new HashMap<>();
            hashMap.put("error","用户未登录");
            return hashMap;
        }

        if(cardId == null){
            Map<Object, Object> hashMap = new HashMap<>();
            hashMap.put("error","cardId 不能为空");
            return hashMap;
        }
        //获取当前时间
        Date date = new Date();
        Map<String, Object> map = new HashMap<>();
        map.put("bdId",cardId);
        //获取最新上报时间
//        Date reportDate = ZhjMqListener.reportDate;
        if(ZhjMqListener.map.get(cardId) != null){
            SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date reportDate = dateFormat.parse(dateFormat.format(ZhjMqListener.map.get(cardId)));
            if(date.getTime() - reportDate.getTime() >= 180000){
                map.put("result",0);
                return map;
            }
            map.put("result",1);
            return map;
        }
        map.put("result",-1);
        //保存日志
        logService.logSave();
        return map;
    }

    /**
     * 平台分页查询终端位置信息
     */
    @ApiOperation("平台分页查询终端位置信息")
    @GetMapping("/v1/getlist")
    public R getList(@MultiRequestBody(required = false) SimplePageParam simplePageParam, @MultiRequestBody(required = false) LocationCondition locationCondition) {

        if(logger.isDebugEnabled()){
            logger.debug(String.format("参数 --> pageParam:{},location:{}", simplePageParam, locationCondition));
        }

        PageParam pageParam = new PageParam();
        if(null != simplePageParam) {
            pageParam.setPageNum(simplePageParam.getPageNum());
            pageParam.setPageSize(simplePageParam.getPageSize());
        }

        LocationEntity location = new LocationEntity();
        if(null != location) {
            location.setCardId(locationCondition.getCardId());
            location.setStartTime(locationCondition.getStartTime());
            location.setEndTime(locationCondition.getEndTime());
        }

        PageResult<LocationEntity> page = locationService.pages(pageParam, location);

        return R.ok().result(page);
    }

    /**
     * 平台数据管理查询位置统计接口
     */
    @ApiOperation("平台数据管理查询位置统计查询")
    @GetMapping("/v1/querybdLoclist")
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
        List<LocVO> list = locationService.getLocData(smsEntity);
        return R.ok().result(list);
    }

    /**
     * 平台数据管理位置在线时长接口
     */
    @ApiOperation("平台数据管理位置在线时长")
    @GetMapping("/v1/getonlinetime")
    public R getOnlineTime(@MultiRequestBody(required = false) SmsCondition smsCondition) {
        if(logger.isDebugEnabled()){
            logger.debug(String.format("参数 --> sms:{}", smsCondition));
        }
        LocationEntity locationEntity = new LocationEntity();
        if(null != smsCondition) {
            locationEntity.setCardId(smsCondition.getSendBdId());
            locationEntity.setStartTime(smsCondition.getStartTime());
            locationEntity.setEndTime(smsCondition.getEndTime());
            locationEntity.setNetworkType(smsCondition.getNetworkType());
            locationEntity.setOffLineTime(smsCondition.getOffLineTime());
        }

        return R.ok().result(locationService.getTimeRate(locationEntity));
    }

    /**
     * 北斗卡在线时长统计数据批量导出
     * @param （startTime、endTime、offLineTime）
     * @throws  Exception
     */
    @ApiOperation("北斗卡在线时长统计数据批量导出")
    @RequestMapping("/v1/successRateBatchOut")
    public void successRateBatchOut(HttpServletResponse response, LocationEntity locationEntity) throws Exception {
        String fileName = "北斗在线时长统计数据.xlsx";
        List<OnLineSumOut> list = locationService.getExcel(locationEntity);
        ExportExcel exportExcel = new ExportExcel("北斗在线时长统计数据", OnLineSumOut.class).setDataList(list);
        exportExcel.write(response, fileName);
        exportExcel.dispose();
    }

    /**
     * 获取所有北斗卡的最新位置
     */
    @ApiOperation("终端最新位置")
    @GetMapping("/v1/getnewloc")
    public R getNewLoc(){
        String[] cards = feignService.getCards();
        List<LocationEntity> list = locationService.queryRecentBdLocation(cards);
        return R.ok().result(list);
    }

    /**
     * 获取缓存里所有北斗卡的最新位置
     */
    @GetMapping("/getloccache")
    public Map getLocCache(){
        Map mapLocation = ZhjMqListener.mapLocation;
        return mapLocation;
    }

}
