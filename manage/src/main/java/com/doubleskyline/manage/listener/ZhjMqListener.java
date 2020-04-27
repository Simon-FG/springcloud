package com.doubleskyline.manage.listener;

import com.alibaba.fastjson.JSON;
import com.doubleskyline.manage.constant.MqConstant;
import com.doubleskyline.manage.modules.bd.entity.LocationEntity;
import com.doubleskyline.manage.modules.bd.entity.MqEntity;
import com.doubleskyline.manage.modules.bd.entity.SmsEntity;
import com.doubleskyline.manage.modules.bd.entity.SosEntity;
import com.doubleskyline.manage.modules.bd.service.LocationService;
import com.doubleskyline.manage.modules.bd.service.SmsService;
import com.doubleskyline.manage.modules.bd.service.SosService;
import com.doubleskyline.manage.util.EncodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * rabbitmq消息队列监听
 * @author  simon
 * @description
 * @date 2020-03-02 16:42
 **/
@Component
@Slf4j
//@RabbitListener(
//        bindings = @QueueBinding(
//                value = @Queue(),
//                exchange = @Exchange(value = "${custom.rabbitMq.tz.exchange}", type = "topic" ,durable = "true"),
//                key = "${custom.rabbitMq.tz.key}")
//)
public class ZhjMqListener {

    //存最新上报时间
    public static Map map = new HashMap<String,Date>();

    public static Map mapLocation = new HashMap<String,LocationEntity>();

    @Autowired
    private SmsService smsService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private SosService sosService;
//    @Autowired
//    private MqService mqService;

    /**
     * 监听消费用户日志
     * @param
     */
//    @RabbitListener(queues = MqConstant.BD_TZ_QUEUE)
    @RabbitHandler
    public void consumeUserLogQueue(Object obj){
         log.info("{}监听到消息： {} ", MqConstant.BD_TZ_QUEUE, obj);
        Message msg = (Message)obj;
        String s = new String(msg.getBody(), StandardCharsets.UTF_8);
        String s1 = EncodeUtil.unicodeToString(s);
        MqEntity entity = JSON.parseObject(s1, MqEntity.class);
        if(null == entity){
            log.error("监听到的消息为空");
        }

        boolean result;
        /**
         * 接收指挥机消息类型 0：用户上报消息（sms->） 1：接收位置消息(新增->location) 2：上报应急消息(sos->) 3：接收通知消息状态（修改->sms） 4：接收指挥机消息（新增->sms）
         */

        // 保存最新上报时间
        if(map.get(entity.getCardId()) != null){
            map.remove(entity.getCardId());
            map.put(entity.getCardId(),new Date());
        }else {
            map.put(entity.getCardId(),new Date());
        }

        switch (entity.getMt()) {
            case 0 :
                // 指挥机发送短信消息(此处不可以有实现)
                break;
            case 1 :
                // 指挥机发送应急救援消息(此处不可以有实现)
                break;
            case 2 :
                //接受位置消息
                LocationEntity locationEntity = new LocationEntity();
                BeanUtils.copyProperties(entity, locationEntity);
                locationEntity.setTerminalId("1");
                locationEntity.setReceiveTime(new Date());

                // 缓存最新位置信息
                if(mapLocation.get(entity.getCardId()) != null){
                    mapLocation.remove(entity.getCardId());
                    mapLocation.put(entity.getCardId(),locationEntity);
                }else {
                    mapLocation.put(entity.getCardId(),locationEntity);
                }

                result = locationService.save(locationEntity);
                log.info("保存位置消息结果{}：{}", result, locationEntity);
                break;
            case 3 :
                // 接受应急求救消息  等待测试
                SosEntity sosEntity = new SosEntity();
                BeanUtils.copyProperties(entity,sosEntity);
                result = sosService.save(sosEntity);
                log.info("保存应急救援消息状态结果{}：{}", result, sosEntity);
                break;
            case 4 :
                // 接收消息状态反馈
                switch (entity.getBt()){
                    //如果是短信消息反馈
                    case 0 :
                        SmsEntity newSmsEntity = new SmsEntity();
                        BeanUtils.copyProperties(entity, newSmsEntity);
                        newSmsEntity.setStatus(entity.getStatus());
                        int i = smsService.updateStatus(newSmsEntity);
                        log.info("更新短信消息状态结果{}：{}", i, newSmsEntity);
                        return;
                    //如果是应急救援消息反馈
                    case 1 :
                        SosEntity newSosEntity = new SosEntity();
                        BeanUtils.copyProperties(entity, newSosEntity);
                        newSosEntity.setStatus(entity.getStatus());
                        int j = sosService.updateStatus(newSosEntity);
                        log.info("更新应急消息状态结果{}：{}", j, newSosEntity);
                        return;
                }
            case 5 :
                // 接受短信消息
                SmsEntity dxEntity = new SmsEntity();
                BeanUtils.copyProperties(entity, dxEntity);
                dxEntity.setDelFlag("0");
                dxEntity.setSendCardId(entity.getCardId());
                dxEntity.setSendTime(entity.getCreateTime());
                dxEntity.setType("0");
                dxEntity.setStatus("0");
                dxEntity.setNetworkType(1);
                result = smsService.save(dxEntity);
                log.info("保存短信通知消息状态结果{}：{}", result, dxEntity);
                break;
            default:
        }

    }

}
