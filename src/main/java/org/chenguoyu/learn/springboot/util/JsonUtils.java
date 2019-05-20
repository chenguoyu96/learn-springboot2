package org.chenguoyu.learn.springboot.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Json工具类
 */
public class JsonUtils {
    private final static ObjectMapper objectMapper = new ObjectMapper();

    private JsonUtils() {
    }

    public static ObjectMapper getInstance() {
        return objectMapper;
    }

    /**
     * JavaBean转化为json字符串
     *
     * @param obj java对象
     * @return
     */
    public static String obj2json(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * json字符串转化为JavaBean
     *
     * @param jsonStr json字符串
     * @return
     */
    public static <T> T json2pojo(String jsonStr, Class<T> clazz) {
        try {
            return objectMapper.readValue(jsonStr, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * json字符串转化为map
     *
     * @param jsonStr json字符串
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> Map<String, Object> json2map(String jsonStr) {
        try {
            return objectMapper.readValue(jsonStr, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * json对象字符串转化为map对象
     *
     * @param jsonStr
     * @param clazz
     * @return
     */
    public static <T> Map<String, T> json2map(String jsonStr, Class<T> clazz) {
        Map<String, Map<String, Object>> map = null;
        try {
            map = objectMapper.readValue(jsonStr, new TypeReference<Map<String, T>>() {
            });
            Map<String, T> result = new HashMap<>();
            for (Map.Entry<String, Map<String, Object>> entry : map.entrySet()) {
                result.put(entry.getKey(), map2pojo(entry.getValue(), clazz));
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * json数组转化为对象数组
     *
     * @param jsonArrayStr json对象数组字符串
     * @param clazz        对象类型
     * @return
     */
    public static <T> List<T> json2list(String jsonArrayStr, Class<T> clazz) {
        List<Map<String, Object>> list = null;
        try {
            list = objectMapper.readValue(jsonArrayStr, new TypeReference<List<T>>() {
            });
            List<T> result = new ArrayList<T>();
            for (Map<String, Object> map : list) {
                result.add(map2pojo(map, clazz));
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * map转化为JavaBean
     *
     * @param map   map对象
     * @param clazz 类型
     * @return
     */
    public static <T> T map2pojo(Map map, Class<T> clazz) {
        return objectMapper.convertValue(map, clazz);
    }
}
