package com.doubleskyline.manage.modules.mq.controller;

import com.doubleskyline.core.model.R;
import com.doubleskyline.manage.modules.bd.entity.SmsEntity;
import com.doubleskyline.manage.modules.mq.service.MqService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags="Mq操作接口")
@RestController
@RequestMapping("/")
@Slf4j
//@Deprecated
public class MqController {

    @Autowired
    private MqService mqService;

    @ApiOperation("发送")
    @GetMapping("/sendMq")
    public R sendMq(String message) {
        mqService.send(message);
        return R.ok();
    }

    @ApiOperation("发送SMS")
    @PostMapping("/testSendSms")
    public R testSendSms(@RequestBody SmsEntity message) {
        mqService.sendForReceiveTest(message);
        return R.ok();
    }
}
