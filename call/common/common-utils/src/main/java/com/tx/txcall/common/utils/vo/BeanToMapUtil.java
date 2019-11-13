package com.tx.txcall.common.utils.vo;

import org.apache.commons.beanutils.BeanUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Map 对象与 JavaBean 对象互转工具类
 */
public class BeanToMapUtil<T> {
 
    private BeanToMapUtil() {
    }
 
    private static final String UNDERLINE="_";
 
    public static String getUnderline() {
        return UNDERLINE;
    }
 
    /**
     * 将 Map对象转化为JavaBean
     * @param map
     * @param T
     * @return
     * @throws Exception
     */
    public static <T> T convertMap2Bean(Map<String, Object> map, Class<T> T) throws Exception {
        if (map == null || map.size() == 0) {
            return null;
        }
        //获取map中所有的key值，全部更新成大写，添加到keys集合中,与mybatis中驼峰命名匹配
        Object mvalue = null;
        Map<String, Object> newMap = new HashMap<>();
        Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
        while(it.hasNext()){
            String key = it.next().getKey();
            mvalue = map.get(key);
            if (key.indexOf(UNDERLINE) != -1) {
                key = key.replaceAll(UNDERLINE, "");
            }
            newMap.put(key.toUpperCase(Locale.US), mvalue);
        }
 
        BeanInfo beanInfo = Introspector.getBeanInfo(T);
        T bean = T.newInstance();
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (int i = 0, n = propertyDescriptors.length; i < n; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            String upperPropertyName = propertyName.toUpperCase();
 
            if (newMap.keySet().contains(upperPropertyName))
            {
                Object value = newMap.get(upperPropertyName);
                //这个方法不会报参数类型不匹配的错误。
                BeanUtils.copyProperty(bean, propertyName, value);
            }
        }
        return bean;
    }
 
 
    /**
     * 将一个 JavaBean 对象转化为一个 Map
     * @param bean
     * @return
     * @throws IntrospectionException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static Map<String, Object> convertBean2Map(Object bean) throws IntrospectionException, IllegalAccessException, InvocationTargetException {
        Class<? extends Object> type = bean.getClass();
        Map<String, Object> returnMap = new HashMap<>();
        BeanInfo beanInfo = Introspector.getBeanInfo(type);
 
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (!"class".equals(propertyName)) {
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(bean, new Object[0]);
                if (result != null) {
                    returnMap.put(propertyName, result);
                } else {
                    returnMap.put(propertyName, null);
                }
            }
        }
        return returnMap;
    }
 
    /**
     * 将 List<Map>对象转化为List<JavaBean>
     * @param listMap
     * @param T
     * @return
     * @throws Exception
     */
    public static <T> List<T> convertListMap2ListBean(List<Map<String, Object>> listMap, Class<T> T) throws Exception {
        List<T> beanList = new ArrayList<>();
        if (listMap != null && !listMap.isEmpty()) {
            for (int i = 0, n = listMap.size(); i < n; i++) {
                Map<String, Object> map = listMap.get(i);
                T bean = convertMap2Bean(map, T);
                beanList.add(bean);
            }
            return beanList;
        }
        return beanList;
    }
 
    /**
     * 将 List<JavaBean>对象转化为List<Map>
     * @param beanList
     * @return
     * @throws Exception
     */
    public static <T> List<Map<String, Object>> convertListBean2ListMap(List<T> beanList, Class<T> T) throws Exception {
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (int i = 0, n = beanList.size(); i < n; i++) {
            Object bean = beanList.get(i);
            Map<String, Object> map = convertBean2Map(bean);
            mapList.add(map);
        }
        return mapList;
    }
}