package com.doubleskyline.manage.modules.mq.service.impl;

import com.alibaba.fastjson.JSON;
import com.doubleskyline.manage.constant.MqConstant;
import com.doubleskyline.manage.modules.mq.service.MqService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 文件信息
 *
 * @Author ZZY
 * @Date 2019-06-01 17:30:22
 */
@Service("mqService")
@Slf4j
public class MqServiceImpl implements MqService {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    //确认消息
//    final RabbitTemplate.ConfirmCallback confirmCallback= new RabbitTemplate.ConfirmCallback() {
//
//        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
//            System.out.println("correlationData: " + correlationData);
//            System.out.println("ack: " + ack);
//            if(!ack){
//                System.out.println("异常处理....");
//            }
//        }
//
//    };

    //return 消息
//    final RabbitTemplate.ReturnCallback returnCallback = new RabbitTemplate.ReturnCallback() {
//
//        public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
//            System.out.println("return exchange: " + exchange + ", routingKey: "
//                    + routingKey + ", replyCode: " + replyCode + ", replyText: " + replyText);
//        }
//    };


    @Override
    public void send(Object entity) {
//        log.info("发送信息到MQ队列{}，信息：{}", MqConstant.BD_MessageDown_QUEUE, entity);

        // MQ推送
//        Map<String, Object> mqMap = new HashMap<>();
//        mqMap.put("MT",0);
//        mqMap.put("SC", 1001);
//        mqMap.put("DC", 459402);
//        mqMap.put("MC", "井口数据需要分析");
//        mqMap.put("ST", "2020-03-25 00:00:00");
//        mqMap.put("ID", 175704);

//        Map<String, Object> mqMap = new HashMap<>();
//        mqMap.put("MT",2);
//        mqMap.put("SC", 1001);
//        mqMap.put("DC", 1002);
//        mqMap.put("MC", "请求救援");
//        mqMap.put("ST", "2020-01-01 00:00:00");
//        mqMap.put("AT", 1);
//        mqMap.put("LO", 120.2323232);
//        mqMap.put("LA", 30.2323212);
//        mqMap.put("DP", "13000000000");
//        mqMap.put("UID", 1);

        //设置 confirm 确认机制逻辑
//        rabbitTemplate.setConfirmCallback(confirmCallback);
        //设置 return 确认机制逻辑
//        rabbitTemplate.setReturnCallback(returnCallback);

//        rabbitTemplate.convertAndSend(MqConstant.BD_MessageDown_EXCHANGE , MqConstant.BD_MessageDown_KEY,JSON.toJSON(mqMap));


    }

    @Override
    public void sendForReceiveTest(Object entity) {
//        log.info("模拟指挥机发送信息到MQ队列{}， 信息：{}", MqConstant.BD_RECEIVE_QUEUE, entity);
//        rabbitTemplate.convertAndSend(MqConstant.BD_RECEIVE_QUEUE, JSON.toJSONString(entity));
    }

}
