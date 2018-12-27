package com.example.demo.seckill.web;

import com.example.demo.seckill.lock.ZookeeperLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @Author: chensai
 * @Date: 2018/12/27 15:45
 * @Version 1.0
 */
@RestController
@RequestMapping("/zoo")
public class ZookeeperCuratorController {


    @Autowired
    private ZookeeperLock zookeeperLock;
    Queue<Integer> queue = new ArrayDeque<>();

    @RequestMapping("/lock")
    public String getRedPack() {
        if (queue.size() > 0) {
            InterProcessMutex lock = zookeeperLock.getLock();
            try {

                try {
                    lock.acquire();
                    System.out.println(queue.poll());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } finally {
                try {
                    lock.release();
                } catch (Exception e) {
                    System.out.println("release:"+e.getMessage());
                }
            }
        } else {
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
