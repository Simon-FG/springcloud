package com.doubleskyline.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 多数据源属性
 */
@Data
@ConfigurationProperties(prefix = "dynamic")
public class DynamicDataSourceProperties {

    private Map<String, DataSourceProperties> datasource = new LinkedHashMap<>();

}
