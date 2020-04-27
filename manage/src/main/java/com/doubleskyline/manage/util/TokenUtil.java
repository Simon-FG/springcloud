package com.doubleskyline.manage.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @Description
 * @auther SIMON
 * @date 2020/4/26
 */

@Component
public class TokenUtil {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    public boolean isToken(String token){

        // *号 必须要加，否则无法模糊查询
        String prefix = "backend-boot_shiro_session_"+"*";
        // 获取所有的key
        Set<String> keys = redisTemplate.keys(prefix);

        if(keys.size() != 0)
        for(String key : keys){
            if(key.length() > prefix.length()){
                String substring = key.substring(key.lastIndexOf("_") + 1, key.length());
                if(token.equals(substring)){
                    return true;
                }
            }
        }

        return false;
    }
}
