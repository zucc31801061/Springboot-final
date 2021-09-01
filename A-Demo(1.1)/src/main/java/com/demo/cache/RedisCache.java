package com.demo.cache;

import com.demo.utils.ApplicationContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Slf4j
public class RedisCache implements Cache {
    private String id;

    public RedisCache(String id){
        log.info("当前缓存的id:[{}]",id);
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    //放入redis缓存
    @Override
    public void putObject(Object key, Object value) {
        log.info("放入缓存的key:[{}] 放入缓存的value:[{}]", key, value);
        getRedisTemplate().opsForHash().put(id,key.toString(),value);
    }

    //从redis缓存获取
    @Override
    public Object getObject(Object key) {
        log.info("获取缓存的key:[{}]", key.toString());
        return getRedisTemplate().opsForHash().get(id, key.toString());
    }

    //删除指定的缓存
    @Override
    public Object removeObject(Object o) {
        return null;
    }

    //清除缓存
    @Override
    public void clear() {
        log.info("清除所有缓存");
        getRedisTemplate().delete(id);
    }

    @Override
    public int getSize() {
        return getRedisTemplate().opsForHash().size(id).intValue();
    }

    public RedisTemplate getRedisTemplate(){
        RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextUtils.getBean("redisTemplate");
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}
