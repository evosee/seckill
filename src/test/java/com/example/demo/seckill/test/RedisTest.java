package com.example.demo.seckill.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.annotation.ApplicationScope;

/**
 * @Author: chensai
 * @Date: 2018/12/20 16:54
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void test(){
        redisTemplate.opsForValue().set("hello","world");
        System.out.println(redisTemplate.opsForValue().get("hello"));

        stringRedisTemplate.opsForValue().set("string","string");

    }
}
