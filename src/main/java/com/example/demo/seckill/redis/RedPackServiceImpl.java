package com.example.demo.seckill.redis;

import com.example.demo.seckill.bean.RedPack;
import com.example.demo.seckill.service.RedPackService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: chensai
 * @Date: 2018/12/20 17:15
 * @Version 1.0
 */
@Service
public class RedPackServiceImpl implements RedPackService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public RedPack getRedPack(String id) {
        String listKey = redisTemplate.opsForValue().get(id);
        String red = redisTemplate.opsForList().rightPopAndLeftPush(listKey, listKey + ":back");
        try {
            if (Strings.isNotBlank(red)) {
                RedPack redPack = new ObjectMapper().readValue(red, RedPack.class);
                return redPack;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String produce(String id) {
        List<String> lists = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            RedPack redPack = new RedPack(String.valueOf(i), "5mao" + i + "fen");
            try {
                lists.add(new ObjectMapper().writeValueAsString(redPack));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        String result = "red:p:" + id;
        redisTemplate.opsForList().leftPushAll(result, lists);
        return result;

    }
}
