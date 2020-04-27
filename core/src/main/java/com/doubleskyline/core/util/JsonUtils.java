package com.doubleskyline.core.util;

import com.doubleskyline.core.exception.BusinessException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;


/**
 * JSON工具类
 */
public class JsonUtils {
    public static ObjectMapper m = new ObjectMapper();

    static {
        // 此配置的作用为当使用此工具将json中的属性还原到bean时，如果有bean中没有的属性，是否报错
        m.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
        m.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    }

    /**
     * json 转换成 bean
     *
     * @param <T>
     * @param json
     * @param clazz
     * @return
     */
    public static <T> T jsonToBean(String json, Class<T> clazz) {
        if (json == null || clazz == null) {
            return null;
        }
        try {
            return m.readValue(json, clazz);
        } catch (IOException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    /**
     * json node 转换成bean
     *
     * @param node
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T nodeToBean(JsonNode node, Class<T> clazz) {
        if (node == null || clazz == null) {
            return null;
        }
        try {
            return m.treeToValue(node,clazz);
        } catch (IOException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    /**
     * bean 转换成 json
     *
     * @param bean
     * @return
     */
    public static String beanToJson(Object bean) {
        if (bean == null) {
            return null;
        }
        try {
            return m.writeValueAsString(bean);
        } catch (IOException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    public static JsonNode stringToJsonNode(String jsonString) {
        if (StringUtils.isBlank(jsonString)) {
            return null;
        }
        try {
            return m.readTree(jsonString);
        } catch (IOException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    public static JsonNode stringToJsonNode(String jsonString, Class c) {
        if (StringUtils.isBlank(jsonString)) {
            return null;
        }
        try {
            return m.readTree(jsonString);
        } catch (IOException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }


    public static <T, A extends List<T>> A jsonToList(String jsonString, TypeReference<List<T>> clazz) {
        if (StringUtils.isBlank(jsonString) || clazz == null) {
            return null;
        }
        try {
            return (A) m.readValue(jsonString, clazz);
        } catch (IOException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

}