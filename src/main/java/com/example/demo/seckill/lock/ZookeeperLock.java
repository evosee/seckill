package com.example.demo.seckill.lock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: chensai
 * @Date: 2018/12/27 16:58
 * @Version 1.0
 */
@Component
public class ZookeeperLock {
    @Autowired
    private CuratorFramework curatorFramework;

    private static final String Lock_PATH = "/lock_path";



    public InterProcessMutex getLock(){
        return new InterProcessMutex(curatorFramework, Lock_PATH);
    }
}
