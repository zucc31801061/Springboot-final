package com.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SpringBootTest
public class DemoApplicationTests {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisTemplate<String,String> redisTemplate;

    @Test
    public void test(){
        ValueOperations<String,String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("test","success");
        String test = valueOperations.get("test");
        System.out.println(test);
    }

    @Test
    public void syncViews() {
        Long startTime = System.nanoTime();
        List dtoList = new ArrayList<>();
        Set<String> keySet = stringRedisTemplate.keys("name::*");
        for (String key : keySet) {
            String views = stringRedisTemplate.opsForValue().get(key);
            String sid = key.replaceAll("name::", "");
            long lid = Long.parseLong(sid);
            long lviews = Long.parseLong(views);
            //删除key
            stringRedisTemplate.delete(key);
        }
    }
}
