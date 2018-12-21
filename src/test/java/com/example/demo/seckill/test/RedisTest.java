package com.example.demo.seckill.test;

import com.example.demo.seckill.bean.RedPack;
import com.example.demo.seckill.service.RedPackService;
import com.example.demo.seckill.web.WebController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

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


    @Test
    public void test() {
        redisTemplate.opsForValue().set("hello", "world");
        System.out.println(redisTemplate.opsForValue().get("hello"));

        stringRedisTemplate.opsForValue().set("string", "string");

    }

    @Test
    public void testP() {
        String key = redPackService.produce("1002");
        //consume(key);

    }

    @Test
    public void testC() {
        consume("red:p:1002");
      //  RedPack redPack = redPackService.getRedPack("red:p:1002");
       // System.out.println(redPack.getMoney());
    }

    private void consume(String key) {
        for (int i = 0; i < 40; i++) {

            Thread thread = new Thread(() -> {
                RedPack redPack = redPackService.getRedPack(key);
                if (redPack != null) {
                    System.out.println(redPack.getMoney());
                } else {
                    System.out.println("抢完了");
                }
            });
            thread.start();

        }
    }

    @Test
    public void testThread(){

        for (int i = 0; i < 40; i++) {

            Thread thread = new Thread(() -> {
                System.out.println(restTemplate.getForEntity("http://localhost:8080/web/v1",String.class));
            });
            thread.start();

        }

    }
}
