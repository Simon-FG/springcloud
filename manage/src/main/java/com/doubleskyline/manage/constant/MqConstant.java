package com.doubleskyline.manage.constant;

/**
 * mq 队列常量
 * @author ZhangZhengyang
 * @description
 * @date 2019-09-06 16:44
 **/
public class MqConstant {

//    public static final String BD_RECEIVE_QUEUE = "bd.receive.queue";

    //北斗下发mq配置
    public static final String BD_MessageDown_QUEUE = "bd.messagedown.queue";
    public static final String BD_MessageDown_EXCHANGE = "MessageDown";
    public static final String BD_MessageDown_KEY = "zyyt.messagedown";

    //北斗上报mq配置
    public static final String BD_TZ_QUEUE = "bd.messageup.queue";

}
