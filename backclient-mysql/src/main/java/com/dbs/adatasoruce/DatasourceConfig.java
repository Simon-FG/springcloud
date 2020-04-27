package com.dbs.adatasoruce;



import javax.sql.DataSource;  
  

import org.springframework.beans.factory.annotation.Value;  
import org.springframework.boot.web.servlet.FilterRegistrationBean;  
import org.springframework.boot.web.servlet.ServletRegistrationBean;  
import org.springframework.context.annotation.Bean;  
import org.springframework.context.annotation.Configuration;  
import org.springframework.context.annotation.Primary;  
  
import com.alibaba.druid.pool.DruidDataSource;  
import com.alibaba.druid.support.http.StatViewServlet;  
import com.alibaba.druid.support.http.WebStatFilter;  
  
@Configuration  
public class DatasourceConfig {  
      
    @Value("${north.datasource.url}")  
    private String dbUrl;  
      
    @Value("${north.datasource.type}")  
    private String dbType;  
      
    @Value("${north.datasource.username}")  
    private String username;  
      
    @Value("${north.datasource.password}")  
    private String password;  
      
    @Value("${north.datasource.driver}")  
    private String driverClassName;  
      
    @Value("${north.datasource.initialSize}")  
    private int initialSize;  
      
    @Value("${north.datasource.minIdle}")  
    private int minIdle;  
      
    @Value("${north.datasource.maxActive}")  
    private int maxActive;  
      
    @Value("${north.datasource.maxWait}")  
    private int maxWait;  
      
    @Value("${north.datasource.timeBetweenEvictionRunsMillis}")  
    private int timeBetweenEvictionRunsMillis;  
      
    @Value("${north.datasource.minEvictableIdleTimeMillis}")  
    private int minEvictableIdleTimeMillis;  
      
    @Value("${north.datasource.validationQuery}")  
    private String validationQuery;  
      
    @Value("${north.datasource.testWhileIdle}")  
    private boolean testWhileIdle;  
      
    @Value("${north.datasource.testOnBorrow}")  
    private boolean testOnBorrow;  
      
    @Value("${north.datasource.testOnReturn}")  
    private boolean testOnReturn;  
      
    @Value("${north.datasource.poolPreparedStatements}")  
    private boolean poolPreparedStatements;  
      

      
    @Value("${north.datasource.useGlobalDataSourceStat}")  
    private boolean useGlobalDataSourceStat;  
      
    @Value("${north.datasource.druidLoginName}")  
    private String druidLoginName;  
      
    @Value("${north.datasource.druidPassword}")  
    private String druidPassword;  
      
    @Bean(name="dataSource",destroyMethod = "close", initMethod="init")  
    @Primary //不要漏了这  
    public DataSource dataSource(){    
        DruidDataSource datasource = new DruidDataSource();    
            datasource.setUrl(this.dbUrl);    
            datasource.setDbType(dbType);  
            datasource.setUsername(username);    
            datasource.setPassword(password);    
            datasource.setDriverClassName(driverClassName);    
            datasource.setInitialSize(initialSize);    
            datasource.setMinIdle(minIdle);    
            datasource.setMaxActive(maxActive);    
            datasource.setMaxWait(maxWait);    
            datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);    
            datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);    
            datasource.setValidationQuery(validationQuery);    
            datasource.setTestWhileIdle(testWhileIdle);    
            datasource.setTestOnBorrow(testOnBorrow);    
            datasource.setTestOnReturn(testOnReturn);    
            datasource.setPoolPreparedStatements(poolPreparedStatements);    
//            datasource.setFilters(filters);    
//            datasource.setConnectionProperties(connectionProperties);  
            datasource.setUseGlobalDataSourceStat(useGlobalDataSourceStat);  
         //   datasource.setValidationQueryTimeout(1);
        return datasource;    
    }  
      
    /////////  下面是druid 监控访问的设置  /////////////////  
    @Bean  
    public ServletRegistrationBean druidServlet() {  
        ServletRegistrationBean reg = new ServletRegistrationBean();  
        reg.setServlet(new StatViewServlet());  
        reg.addUrlMappings("/druid/*");  //url 匹配  
//        reg.addInitParameter("allow", "10.10.1.151,127.0.0.1"); // IP白名单 (没有配置或者为空，则允许所有访问)  
//        reg.addInitParameter("deny", "10.10.1.73"); //IP黑名单 (存在共同时，deny优先于allow)  
        reg.addInitParameter("loginUsername", this.druidLoginName);//登录名  
        reg.addInitParameter("loginPassword", this.druidPassword);//登录密码  
        reg.addInitParameter("resetEnable", "false"); // 禁用HTML页面上的“Reset All”功能  
        return reg;  
    }  
  
    @Bean(name="druidWebStatFilter")  
    public FilterRegistrationBean filterRegistrationBean() {  
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();  
        filterRegistrationBean.setFilter(new WebStatFilter());  
        filterRegistrationBean.addUrlPatterns("/*");  
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*"); //忽略资源  
        filterRegistrationBean.addInitParameter("profileEnable", "true");  
        filterRegistrationBean.addInitParameter("principalCookieName", "USER_COOKIE");  
        filterRegistrationBean.addInitParameter("principalSessionName", "USER_SESSION");  
        return filterRegistrationBean;  
    }  
     
}  