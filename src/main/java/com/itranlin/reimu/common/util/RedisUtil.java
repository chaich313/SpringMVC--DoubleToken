package com.itranlin.reimu.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author itranlin
 */
@Slf4j
public class RedisUtil {

    private static final StringRedisTemplate STRING_REDIS_TEMPLATE = SpringUtils.getBean(StringRedisTemplate.class);

    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     * @return 设置是否成功
     */
    public static boolean expire(String key, long time) {
        if (time > 0) {
            return RedisUtil.expire(key, time, TimeUnit.SECONDS);
        }
        return false;
    }

    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     * @return 设置是否成功
     */
    public static boolean expire(String key, long time, TimeUnit unit) {
        if (time > 0) {
            STRING_REDIS_TEMPLATE.expire(key, time, unit);
        }
        return true;
    }

    /**
     * 根据key 获取过期时间
     *
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    @SuppressWarnings("AlibabaMethodReturnWrapperType")
    public static long getExpire(String key) {
        Long result = STRING_REDIS_TEMPLATE.getExpire(key, TimeUnit.SECONDS);
        if (result == null) {
            return 0L;
        }
        return result;
    }

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    @SuppressWarnings("AlibabaMethodReturnWrapperType")
    public static boolean hasKey(String key) {
        Boolean result = STRING_REDIS_TEMPLATE.hasKey(key);
        if (result == null) {
            result = false;
        }
        return result;
    }

    /**
     * 删除缓存
     *
     * @param key 可以传一个值 或多个
     */
    public static void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                STRING_REDIS_TEMPLATE.delete(key[0]);
            } else {
                STRING_REDIS_TEMPLATE.delete(Arrays.asList(key));
            }
        }
    }

    /**
     * 普通缓存获取
     *
     * @param key 键
     * @return 值
     */
    public static String get(String key) {
        return key == null ? null : STRING_REDIS_TEMPLATE.opsForValue().get(key);
    }

    /**
     * 普通缓存获取
     *
     * @param key 键
     * @return 值
     */
    public static Set<String> getKeys(String key) {
        return key == null ? null : STRING_REDIS_TEMPLATE.keys(key);
    }

    /**
     * 普通缓存放入
     *
     * @param key   键
     * @param value 值
     */
    public static void set(String key, String value) {
        STRING_REDIS_TEMPLATE.opsForValue().set(key, value);
    }

    /**
     * 普通缓存放入并设置时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     */
    public static void set(String key, String value, long time) {
        if (time > 0) {
            STRING_REDIS_TEMPLATE.opsForValue().set(key, value, time, TimeUnit.SECONDS);
        } else {
            set(key, value);
        }
    }
}
