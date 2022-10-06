package com.wds.common.Utils;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.Set;

/**
 * @author WDs , wds8.24@outlook.com
 * @version 1.0
 * @since 2022-10-06 20:44
 */
public class RedisUtil {
    public static void clearCache(String cacheKey, RedisTemplate<Object, Object> redisTemplate) {
        Set<Object> keys = redisTemplate.keys(cacheKey);
        if (keys != null) {
            redisTemplate.delete(keys);
        }
    }
}
