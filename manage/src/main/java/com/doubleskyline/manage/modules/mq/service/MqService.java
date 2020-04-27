package com.doubleskyline.manage.modules.mq.service;

/**
 * 文件信息
 *
 * @Author ZZY
 * @Date 2019-06-01 17:30:22
 */
public interface MqService {

    void send(Object message);

    @Deprecated
    void sendForReceiveTest(Object message);
}

