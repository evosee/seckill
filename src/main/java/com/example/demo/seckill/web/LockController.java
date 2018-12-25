package com.example.demo.seckill.web;

import com.example.demo.seckill.lock.RedisLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @Author: chensai
 * @Date: 2018/12/20 16:51
 * @Version 1.0
 */
@RestController
@RequestMapping("/web")
public class LockController {

    @Autowired
    private RedisLock redisLock;
    Queue<Integer> queue = new ArrayDeque<>();

    @RequestMapping("/lock")
    public String getRedPack() {
        String uuid = UUID.randomUUID().toString();
        if (queue.size() > 0) {
            try {

                if (redisLock.lock(uuid)) {
                    //System.out.println("加载成功");
                    System.out.println(queue.poll());
                }

            } finally {
                System.out.println("释放锁：" + redisLock.unlock(uuid));
            }
        }else{
            System.out.println("没了哦");
        }


        return "yes";
    }

    @RequestMapping("/lock/producer")
    public String producer() {
        for (int i = 0; i < 10; i++) {
            queue.add(i);
        }
        return "success";
    }


}
