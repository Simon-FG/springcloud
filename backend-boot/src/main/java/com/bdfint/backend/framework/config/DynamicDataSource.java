package com.bdfint.backend.framework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.github.pagehelper.PageHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 动态数据源切换
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    /**
     * 代码中的determineCurrentLookupKey方法取得一个字符串，
     * 该字符串将与配置文件中的相应字符串进行匹配以定位数据源，配置文件，即applicationContext.xml文件中需要要如下代码：(non-Javadoc)
     *
     * @see org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource#determineCurrentLookupKey()
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return getDataSourceType();
    }

    /**
     * 当使用ThreadLocal维护变量时，ThreadLocal为每个使用该变量的线程提供独立的变量副本，
     * 所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本。
     */
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static void setDataSourceType(String dataSourceType) {
        contextHolder.set(dataSourceType);
    }

    public static String getDataSourceType() {
        return contextHolder.get();
    }

    public static void clearDataSourceType() {
        contextHolder.remove();
    }

    /**
     * 管理所有的数据源id;
     * 主要是为了判断数据源是否存在;
     */
    public static List<String> dataSourceIds = new ArrayList<>();

    /**
     * 判断指定DataSrouce当前是否存在
     *
     * @param dataSourceId
     */
    public static boolean containsDataSource(String dataSourceId) {
        return dataSourceIds.contains(dataSourceId);
    }
   
    /**  
     * 分页插件  
     * @param dataSource  
     * @return  
     */  
      
//    @Bean  
//    public PageHelper pageHelper() {  
//        PageHelper pageHelper = new PageHelper();  
//        Properties p = new Properties();  
//        p.setProperty("offsetAsPageNum", "true");  
//        p.setProperty("rowBoundsWithCount", "true");  
//        p.setProperty("reasonable", "true");  
//        p.setProperty("returnPageInfo", "check");  
//        p.setProperty("params", "count=countSql");  
//        pageHelper.setProperties(p);  
//        return pageHelper;  
//    } 
    
}
