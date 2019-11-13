package com.tx.txcall.common.components;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author zengxiaopeng
 */

@Component
public class RedisHandler {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 批量删除对应的value
     *
     * @param keys
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 批量删除key
     *
     * @param pattern
     */
    public void removePattern(final String pattern) {
        Set<String> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0) {
            redisTemplate.delete(keys);
        }
    }

    /**
     * 删除对应的value
     *
     * @param key
     */
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * 缓存是否存在
     *
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public Object get(final String key) {
        Object result = null;
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }

    /**
     * @param key
     * @param hashKey
     * @return
     */
    public Object get(final String key, final String hashKey) {
        Object result = null;
        HashOperations<String, Object, Object> operations = redisTemplate.opsForHash();
        result = operations.get(key, hashKey);
        return result;
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value) {
        boolean result = false;
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        operations.set(key, value);
        redisTemplate.expire(key, 1, TimeUnit.DAYS);
        result = true;
        return result;
    }

    /**
     * @param key
     * @param hashKey
     * @param value
     * @return
     */
    public boolean set(final String key, final String hashKey, Object value) {
        boolean result = false;
        HashOperations<String, Object, Object> operations = redisTemplate.opsForHash();
        operations.put(key, hashKey, value);
        redisTemplate.expire(key, 1, TimeUnit.DAYS);
        result = true;
        return result;
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        operations.set(key, value);
        redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
        result = true;
        return result;
    }

    /**
     * 获取keys
     *
     * @param pattern
     * @return
     */
    public Set getKeys(String pattern) {
        if (StringUtils.isEmpty(pattern)) {
            return redisTemplate.keys("*");
        } else {
            return redisTemplate.keys(pattern);
        }
    }
}
