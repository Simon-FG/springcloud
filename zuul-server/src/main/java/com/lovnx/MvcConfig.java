package com.lovnx;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @description:
 * @author: LSL
 * @date: 2020/4/2
 */
@Configuration
public class MvcConfig extends WebMvcConfigurationSupport {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/end_pic_uploads/**").addResourceLocations("file:D:/D/upload/minhang/end_pic_uploads/");
        registry.addResourceHandler("/menu/**").addResourceLocations("file:D:/D/upload/minhang/menu/");
    }
}
