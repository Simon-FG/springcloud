package com.doubleskyline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 系统管理启动类
 *
 * @Auther: ZZY
 * @Date: 2019/9/27
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableScheduling
public class ManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageApplication.class, args);
    }

}
