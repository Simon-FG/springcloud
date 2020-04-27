package com.doubleskyline.core.util;

import java.util.Map;

/**
 * 字符串工具类
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    /**
     * 根据变量表达式（例："{id}"或"{info.name}"）替换内容
     * @param s
     * @param map
     * @return
     */
    public static String replaceVariables(String s, Map<String,String> map){
        final String[] ss = new String[]{s};
        map.forEach((key, value) -> ss[0] = ss[0].replaceAll("\\{\\s*"+key.replaceAll("\\.","\\\\.")+"\\s*\\}", value));
        return ss[0];
    }

}
