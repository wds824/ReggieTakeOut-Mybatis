package com.wds.common.Utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Set;

/**
 * @author WDs , wds8.24@outlook.com
 * @version 1.0
 * @since 2022-10-06 20:44
 */
@Slf4j
public class RedisUtil {

    @Autowired
    private static RedisTemplate<Object,Object> template;

    public static void clearCache(String cacheKey) {
        Set<Object> keys = template.keys(cacheKey);
        if (keys != null) {
            log.info("清空缓存: {}",cacheKey );
            template.delete(keys);
        }
    }

    public static void cleanAllCache(){
        Set<Object> keys = template.keys("*");
        if (keys == null) {
            log.info("清空缓存: {}","缓存为空，不需要操作。" );
            return;
        }
        log.info("清空缓存: {}","all" );
        template.delete(keys);
    }
}
