package com.doubleskyline.manage.modules.bd.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description
 * @auther SIMON
 * @date 2020/4/20
 */
@FeignClient(value = "useradmin")
@Component
public interface FeignService {

//    @RequestMapping(value = "/admin/feignaction/getcards")
    @RequestMapping(value = "/noUser/register/getcards")
    String[] getCards();
}
