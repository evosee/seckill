package com.example.demo.seckill.test;

import com.example.demo.seckill.lock.RedisLock;
import com.example.demo.seckill.service.RedPackService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

/**
 * @Author: chensai
 * @Date: 2018/12/20 16:54
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedPackService redPackService;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RedisLock redisLock;


    @Test
    public void test() {
        redisTemplate.opsForValue().set("hello", "world");
        System.out.println(redisTemplate.opsForValue().get("hello"));

        stringRedisTemplate.opsForValue().set("string", "string");

    }

    @Test
    public void testP() {
        String key = redPackService.produce("1005");
        //consume(key);

    }

    @Test
    public void testThread(){
        System.out.println("123");
        System.out.println(restTemplate.getForEntity("http://localhost:8080/web/v1?id={0}",String.class,1005));
        for (int i = 0; i < 1000; i++) {

            Thread thread = new Thread(() -> {
                System.out.println(restTemplate.getForEntity("http://localhost:8080/web/v1?id={0}",String.class,1005));
            });
            thread.start();

        }

    }

    @Test
    public void testLock(){
        String uuid = UUID.randomUUID().toString();
        if(redisLock.lock(uuid)){
            System.out.println("加载成功");
        }

        redisLock.unlock(uuid);
    }

    @Test
    public void testPRedisLock(){
        System.out.println(restTemplate.getForEntity("http://localhost:8080/web/lock/producer",String.class));
    }

    @Test
    public void testRedisLockThread(){
        System.out.println("redisLock");
        for (int i = 0; i < 10; i++) {

            Thread thread = new Thread(() -> {
                System.out.println(restTemplate.getForEntity("http://localhost:8080/web/lock",String.class));
            });
            thread.start();

        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
