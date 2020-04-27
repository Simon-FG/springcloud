package com.doubleskyline.core.util;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class MapUtils {

    public static Map<String,String[]> filterEmptyParam(Map<String,String[]> param){
        return Maps.filterValues(param, s -> s != null && s.length > 0 && StringUtils.isNotBlank(s[0]));
    }
}