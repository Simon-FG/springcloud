package com.doubleskyline.manage.listener;

import com.alibaba.fastjson.JSON;
import com.doubleskyline.manage.constant.MqConstant;
import com.doubleskyline.manage.modules.bd.entity.MqEntity;
import com.doubleskyline.manage.modules.mq.service.MqService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * rabbitmq消息队列监听
 * @author ZhangZhengyang
 * @description
 * @date 2019-09-06 16:42
 **/
@Component
@Slf4j
@Deprecated
public class TestMqListener {

    @Autowired
    private MqService mqService;

    /**
     * 监听消费用户日志
     * @param message
     */
//    @RabbitListener(queuesToDeclare = @Queue(MqConstant.BD_SEND_QUEUE), containerFactory = "singleListenerContainer")
//    @RabbitListener(queues = MqConstant.BD_RECEIVE_QUEUE)
    @Deprecated
    public void consumeUserLogQueue(@Payload String message){
//        log.info("{}监听到消息： {} ", MqConstant.BD_RECEIVE_QUEUE, message);

        MqEntity entity = JSON.parseObject(message, MqEntity.class);
        if(null == entity){
            log.error("监听到的消息为空");
        }

        /**
         * 接收指挥机消息类型 0：用户上报消息（sms->） 1：接收位置消息(新增->location) 2：上报应急消息(sos->) 3：接收通知消息状态（修改->sms） 4：接收指挥机消息（新增->sms）
         */
        switch (entity.getMt()) {
            case 0 :
                // 用户上报消息(此处不可以有实现)
                testReceiveSms(entity);
                break;
            case 1 :
                // 接收位置消息
                break;
            case 2 :
                // 上报应急消息(此处不可以有实现)
                break;
            case 3 :
                // 接收通知消息状态
                break;
            case 4 :
                // 接收指挥机消息
                break;
            default:
        }

    }

    @Deprecated
//    @RabbitListener(queues = MqConstant.BD_RECEIVE_QUEUE)
    private void testReceiveSms(MqEntity entity) {

        entity.setMt(3);
        entity.setStatus("1");
        entity.setId(entity.getId());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("模拟指挥机发送通知消息状态到MQ {}", entity);
        mqService.sendForReceiveTest(entity);
    }

}
