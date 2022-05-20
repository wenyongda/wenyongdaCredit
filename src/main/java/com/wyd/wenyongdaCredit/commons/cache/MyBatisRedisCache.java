package com.wyd.wenyongdaCredit.commons.cache;



import com.wyd.wenyongdaCredit.commons.util.ApplicationContextUtil;
import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.DigestUtils;

import java.util.concurrent.TimeUnit;


public class MyBatisRedisCache implements Cache {
    private static final Logger LOG = LoggerFactory.getLogger(MyBatisRedisCache.class);

    private final String id;

    public MyBatisRedisCache(final String id) {
        if(id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        LOG.info("Redis Cache id : " + id);
        this.id = id;
    }
    
    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void putObject(Object key, Object value) {
        if(value != null) {
            getRedisTemplate().opsForHash().put(id,keyToMD5(key.toString()),value);
            getRedisTemplate().expire(keyToMD5(key.toString()),2, TimeUnit.DAYS);
        }

    }

    @Override
    public Object getObject(Object key) {
        try {
            if(key != null) {
                // 根据key取出redis中的hash中的数据
                return getRedisTemplate().opsForHash().get(id, keyToMD5(key.toString()));
            }
        } catch (Exception e) {
            LOG.error("redis ");
        }
        return null;
    }

    @Override
    public Object removeObject(Object key) {
        try {
            if(key != null) {
                getRedisTemplate().expire(keyToMD5(key.toString()), 1, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public void clear() {
        getRedisTemplate().delete(id);
    }

    @Override
    public int getSize() {
        return getRedisTemplate().opsForHash().size(id).intValue();
    }

    // 把通用的代码封装成方法
    private RedisTemplate getRedisTemplate(){
        // 通过ApplicationContextUtil获取到RedisTemplate
        RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextUtil.getBean("redisTemplate");
        StringRedisSerializer stringRedisSerializer = (StringRedisSerializer) ApplicationContextUtil.getBean("stringRedisSerializer");
        // 设置key的序列化方案
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);

        return redisTemplate;
    }

    // 封装一个对key进行处理的方法
    private String keyToMD5(String key){
        return DigestUtils.md5DigestAsHex(key.getBytes());
    }


}
