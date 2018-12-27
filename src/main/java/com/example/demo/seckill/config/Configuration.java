package com.example.demo.seckill.config;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: chensai
 * @Date: 2018/12/27 16:16
 * @Version 1.0
 */
@org.springframework.context.annotation.Configuration
public class Configuration {
    @Value("${spring.redis.host}")
    private String host;

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public CuratorFramework getCuratorFramework(){
        CuratorFramework client = CuratorFrameworkFactory.newClient(host+":2181", new RetryNTimes(10, 5000));
        client.start();
        return client;
    }
}
