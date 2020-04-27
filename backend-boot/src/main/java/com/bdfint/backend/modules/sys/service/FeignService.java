package com.bdfint.backend.modules.sys.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * @Description
 * @auther SIMON
 * @date 2020/4/24
 */
@FeignClient(value = "manage")
@Component
public interface FeignService {

    @RequestMapping(value = "/getloccache",method = {RequestMethod.GET})
    Map getLocCache();
}
