package com.example.demo.seckill.lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * @Author: chensai
 * @Date: 2018/12/24 17:17
 * @Version 1.0
 */
@Component
public class RedisLock {
    private static final String KEY = "redis:lock:";
    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    public Boolean lock(String id) {

        while (true) {
            if (redisTemplate.opsForValue().setIfAbsent(KEY, id, 1L, TimeUnit.MINUTES))
                break;
        }
        return true;
    }

    public boolean unlock(String id) {
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Long result = (Long) redisTemplate.execute(new DefaultRedisScript(script, Long.class), Collections.singletonList(KEY), id);
        if (result.equals(1L)) {
            return true;
        }
        return false;

    }


}
