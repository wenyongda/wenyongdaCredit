package com.wyd.wenyongdaCredit.commons.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

public class RedisUtil {
    // 把通用的代码封装成方法
    public static RedisTemplate<String, Object> getRedisTemplate(){
        // 通过ApplicationContextUtil获取到RedisTemplate
        RedisTemplate<String, Object>  redisTemplate = (RedisTemplate<String, Object>) ApplicationContextUtil.getBean("redisTemplate");
        //StringRedisSerializer stringRedisSerializer = (StringRedisSerializer) ApplicationContextUtil.getBean("stringRedisSerializer");
        RedisSerializer<String> stringRedisSerializer = new StringRedisSerializer();
        // 设置key的序列化方案
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(stringRedisSerializer);
        return redisTemplate;
    }
}
