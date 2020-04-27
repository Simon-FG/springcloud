package com.dbs;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.dbs.service.impl.ISysCpService;
@EnableDiscoveryClient
//启动springboot
@SpringBootApplication
//加载配置
@EnableAutoConfiguration  
//需要扫描的持久层
@MapperScan(basePackages={"com.dbs.mapper"})  
@ComponentScan(basePackages={"com.dbs"})  
//开始事物管理
@EnableTransactionManagement
public class NorthApplication extends SpringBootServletInitializer implements EmbeddedServletContainerCustomizer,ApplicationListener<ContextRefreshedEvent>{  
	@Autowired
	private ISysCpService sysCpService;
	@Override    
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {    
        return application.sources(NorthApplication.class);    
    }    
  
    public static void main(String[] args) throws Exception {  
            SpringApplication.run(NorthApplication.class, args);  
            System.out.println("=======================NorthApplication=============启动成功");
            
        }  
  
    public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {  
     // configurableEmbeddedServletContainer.setPort(9090);  
    }

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		
	} 
}