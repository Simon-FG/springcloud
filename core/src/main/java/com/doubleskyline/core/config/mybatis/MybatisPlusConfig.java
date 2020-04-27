package com.doubleskyline.core.config.mybatis;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.extension.plugins.SqlExplainInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.transaction.TransactionFactory;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

/**
 * mybatis-plus配置
 *
 * @author zzy
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * 乐观锁插件
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }


    @Bean
    public TransactionFactory transactionFactory(){
        return new SpringManagedTransactionFactory();
    }

    @Primary
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource)
            throws Exception {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setTransactionFactory(transactionFactory());
        bean.setPlugins(new Interceptor[]{paginationInterceptor(), optimisticLockerInterceptor()});
//        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/**/*Dao.xml"));// 扫描指定目录的xml
        return bean.getObject();
    }

    /**
     * SQL执行效率插件
     */
    @Bean
    @Profile({"dev","test"})// 设置 dev test 环境开启
    @ConditionalOnProperty(prefix = "mybatis-plus.sql-analyse", name = "show", havingValue = "true", matchIfMissing = true)
    public PerformanceInterceptor performanceInterceptor(@Value("${mybatis-plus.sql-analyse.format:true}") Boolean format) {
        PerformanceInterceptor interceptor = new PerformanceInterceptor();
        // SQL是否格式化 默认false
        interceptor.setFormat(format);
        return interceptor;
    }

    /**
     * 执行分析插件
     * SQL 执行分析拦截器【 目前只支持 MYSQL-5.6.3 以上版本 】，作用是分析 处理 DELETE UPDATE 语句， 防止小白或者恶意 delete update 全表操作！
     * @SqlParser(filter = true) 跳过该方法解析
     */
    @Bean
    @Profile({"dev","test"})// 设置 dev test 环境开启
    public SqlExplainInterceptor sqlExplainInterceptor() {
        return new SqlExplainInterceptor();
    }


}
