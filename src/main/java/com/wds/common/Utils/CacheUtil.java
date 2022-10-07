package com.wds.common.Utils;

import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Set;

/**
 * @author WDs , wds8.24@outlook.com
 * @version 1.0
 * @since 2022-10-06 20:44
 *
 * redis工具类
 * 包括对于缓存的存储，读取，清空等方法
 */
@Slf4j
public class CacheUtil {


    private static RedisTemplate<Object, Object> template = null;
    static {
        template = SpringUtil.getBean("redisTemplate");
    }
    public static void clearCache(String cacheKey) {
        Set<Object> keys = template.keys(cacheKey);
        if (keys != null) {
            log.info("清空缓存: {}", cacheKey);
            template.delete(keys);
        }
    }

    public static void clearAllCache() {
        Set<Object> keys = template.keys("*");
        if (keys == null) {
            log.info("清空缓存: {}", "缓存为空，不需要操作。");
            return;
        }
        log.info("清空缓存: {}", "all");
        template.delete(keys);
    }

    public static Object readCache(String cacheName) {
        log.info("读取缓存: {}", cacheName);
        return template.opsForValue().get(cacheName);
    }

    public static void saveCache(String cacheName, Object data){
        log.info("存入缓存: {}", cacheName);
        template.opsForValue().set(cacheName, data);
    }
}
