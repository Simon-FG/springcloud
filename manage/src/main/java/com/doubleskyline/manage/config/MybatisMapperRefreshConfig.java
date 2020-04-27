package com.doubleskyline.manage.config;


import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.doubleskyline.core.config.mybatis.MybatisMapperRefresh;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * MybatisPlus xml 热加载
 * @author zzy
 * @date 2019-05-31
 */
@Configuration
@Profile({"dev"})
public class MybatisMapperRefreshConfig {

    @Autowired
    private MybatisPlusProperties properties;
    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    @Value("${mybatis-plus.mybatis-refresh.delaySeconds: 20}")
    private int delaySeconds;
    @Value("${mybatis-plus.mybatis-refresh.sleepSeconds: 3}")
    private int sleepSeconds;
    @Value("${mybatis-plus.mybatis-refresh.enabled: true}")
    private boolean enabled;

    @Bean
    public MybatisMapperRefresh mapperRefresh() {
        System.setProperty("user.timezone","GMT +08");
        MybatisMapperRefresh mybatisMapperRefresh = new MybatisMapperRefresh(
                properties.resolveMapperLocations(), sqlSessionFactory, delaySeconds, sleepSeconds, enabled
        );
        return mybatisMapperRefresh;
    }

}
