package com.itranlin.basic.common.handler;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @author itranlin
 */
@Slf4j
public class BaseContextHandler {
    public static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<>();

    public static void set(String key, Object value) {
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<>(8);
            threadLocal.set(map);
        }
        map.put(key, value);
    }

    public static Object get(String key) {
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<>(8);
            threadLocal.set(map);
        }
        return map.get(key);
    }


    public static String getUsername() {
        Object value = get("username");
        return returnObjectValue(value);
    }

    public static void setUsername(String username) {
        set("username", username);
    }

    public static String getToken() {
        Object value = get("Authorization");
        return StringHelper.getObjectValue(value);
    }

    public static void setToken(String token) {
        set("Authorization", token);
    }

    public static String getId() {
        Object value = get("id");
        return StringHelper.getObjectValue(value);
    }

    public static void setId(String id) {
        set("id", id);
    }

    public static String getRole() {
        Object value = get("role");
        return StringHelper.getObjectValue(value);
    }

    public static void setRole(String role) {
        set("role", role);
    }

    private static String returnObjectValue(Object value) {
        return value == null ? null : value.toString();
    }

    public static void remove() {
        threadLocal.remove();
    }


}
