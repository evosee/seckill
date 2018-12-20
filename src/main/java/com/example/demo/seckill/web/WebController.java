package com.example.demo.seckill.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: chensai
 * @Date: 2018/12/20 16:51
 * @Version 1.0
 */
@RestController("/web")
public class WebController {
    @Autowired
    private RedisTemplate redisTemplate;


}
