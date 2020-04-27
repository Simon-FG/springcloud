package com.doubleskyline.manage.modules.bd.controller;

import com.alibaba.fastjson.JSON;
import com.doubleskyline.core.controller.BaseController;
import com.doubleskyline.core.model.R;
import com.doubleskyline.core.util.StringUtils;
import com.doubleskyline.manage.constant.MqConstant;
import com.doubleskyline.manage.modules.bd.entity.SosEntity;
import com.doubleskyline.manage.modules.bd.service.LogService;
import com.doubleskyline.manage.modules.bd.service.SosService;
import com.doubleskyline.manage.util.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 北斗应急求救
 *
 * @author ZZY
 * @date 2020-02-21 11:38:26
 */
@Api(tags = "北斗应急求救")
@RestController
@RequestMapping("/")
@Slf4j
public class SosController extends BaseController {

    @Autowired
    private SosService sosService;
    @Autowired
    private LogService logService;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private TokenUtil tokenUtil;

    /**
     * 北斗应急求救接口
     */
    @ApiOperation("北斗应急求救接口")
    @PostMapping("/v1/sos")
    public R sos(SosEntity sos , String token) {

        if(StringUtils.isBlank(token)){
            return R.error("token 不能为空");
        }

        if(!tokenUtil.isToken(token)){
            return R.error("用户未登录");
        }

        if(logger.isDebugEnabled()){
            logger.debug(String.format("参数 --> sos:{}", sos));
        }

        sos.setDelFlag("0");
        sos.setStatus("2");
        sos.setTime(new Date());
        sosService.insertSos(sos);

        //将应急求救消息推到MQ
        Map<String, Object> mqMap = new HashMap<>();
        mqMap.put("MT", 1);
        mqMap.put("SC", 0);
        mqMap.put("DC", sos.getDestDbId() == null ? null : Integer.valueOf(sos.getDestDbId()));
        mqMap.put("ST", sos.getTime() == null ? null : sos.getTime().toString());
        mqMap.put("ID", sos.getId());
        mqMap.put("MC", sos.getMsg());
        rabbitTemplate.convertAndSend(MqConstant.BD_MessageDown_EXCHANGE , MqConstant.BD_MessageDown_KEY, JSON.toJSON(mqMap));

        //保存日志信息
        logService.logSave();
        return R.ok();
    }

    /**
     * 监视平台查询最新五条报警求救
     * @return
     */
    @ApiOperation("监视平台查询最新五条报警求救")
    @GetMapping("/sos/v1/getlistnew5")
    public R getListNew5() {
        return R.ok().result(sosService.getListNew5());
    }

}
