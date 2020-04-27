package com.doubleskyline.core.util;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author zhuxiaomeng
 * @date 2017/12/18.
 * @email 154040976@qq.com
 * 对象操作
 */
@Slf4j
public class BeanUtils {

  public static String[] getNullPropertyNames(Object source) {
    final BeanWrapper src = new BeanWrapperImpl(source);
    PropertyDescriptor[] pds = src.getPropertyDescriptors();

    Set<String> emptyNames = new HashSet<>();
    for (PropertyDescriptor pd : pds) {
      Object srcValue = src.getPropertyValue(pd.getName());
      if (srcValue == null){
        emptyNames.add(pd.getName());
      }
    }
    String[] result = new String[emptyNames.size()];
    return emptyNames.toArray(result);
  }

  /**
   * 非空拷贝
   * @param source
   * @param target
   */
  public static void copyNotNullBean(Object source, Object target) {
    org.springframework.beans.BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
  }

  public static Map<String, String> bean2MapForAllField(Object bean){
      JsonNode jsonNode = JsonUtils.stringToJsonNode(JsonUtils.beanToJson(bean));
      Map<String,String> map = new HashMap<>(jsonNode.size());
      json2Map(jsonNode, map, "");
      return map;
  }

  private static void json2Map(JsonNode jsonNode, final Map<String,String> map, final String keyPrefix){
      jsonNode.fields().forEachRemaining(entry -> {
        if(entry.getValue().isArray() || entry.getValue().isObject()){
          json2Map(entry.getValue(), map, entry.getKey()+".");
        }else{
          map.put(keyPrefix + entry.getKey(), entry.getValue().textValue());
        }
      });
  }


  /**
   * 使用Introspector，map集合成javabean
   *
   * @param map       map
   * @param beanClass bean的Class类
   * @return bean对象
   */
  public static <T> T mapToBean(Map<String, Object> map, Class<T> beanClass) {

    if (MapUtils.isEmpty(map)) {
      return null;
    }

    try {
      T t = beanClass.newInstance();

      BeanInfo beanInfo = Introspector.getBeanInfo(t.getClass());
      PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
      for (PropertyDescriptor property : propertyDescriptors) {
        Method setter = property.getWriteMethod();
        if (setter != null) {
          setter.invoke(t, map.get(property.getName()));
        }
      }
      return t;
    } catch (Exception ex) {
      log.error("########map集合转javabean出错######，错误信息，{}", ex.getMessage());
      throw new RuntimeException();
    }

  }

  /**
   * 使用Introspector，对象转换为map集合
   *
   * @param beanObj javabean对象
   * @return map集合
   */
  public static Map<String, Object> beanToMap(Object beanObj) {

    if (null == beanObj) {
      return null;
    }

    Map<String, Object> map = new HashMap<>();

    try {
      BeanInfo beanInfo = Introspector.getBeanInfo(beanObj.getClass());
      PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
      for (PropertyDescriptor property : propertyDescriptors) {
        String key = property.getName();
        if (key.compareToIgnoreCase("class") == 0) {
          continue;
        }
        Method getter = property.getReadMethod();
        Object value = getter != null ? getter.invoke(beanObj) : null;
        map.put(key, value);
      }

      return map;
    } catch (Exception ex) {
      log.error("########javabean集合转map出错######，错误信息，{}", ex.getMessage());
      throw new RuntimeException();
    }
  }


  /**
   * 判断对象属性是否是基本数据类型,包括是否包括string
   * @param clazz
   * @return
   */
  public static boolean isBaseType(Class clazz) {
    return isBaseType(clazz,true);
  }
  /**
   * 判断对象属性是否是基本数据类型,包括是否包括string
   * @param clazz
   * @param incString 是否包括string判断,如果为true就认为string也是基本数据类型
   * @return
   */
  public static boolean isBaseType(Class clazz, boolean incString) {
    if (incString && clazz.equals(String.class)) {
      return true;
    }
    return clazz.equals(Integer.class) ||
            clazz.equals(int.class) ||
            clazz.equals(Byte.class) ||
            clazz.equals(byte.class) ||
            clazz.equals(Long.class) ||
            clazz.equals(long.class) ||
            clazz.equals(Double.class) ||
            clazz.equals(double.class) ||
            clazz.equals(Float.class) ||
            clazz.equals(float.class) ||
            clazz.equals(Character.class) ||
            clazz.equals(char.class) ||
            clazz.equals(Short.class) ||
            clazz.equals(short.class) ||
            clazz.equals(Boolean.class) ||
            clazz.equals(boolean.class);
  }
}
