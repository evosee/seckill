package com.example.demo.seckill.service;

import com.example.demo.seckill.bean.RedPack;

/**
 * @Author: chensai
 * @Date: 2018/12/20 17:11
 * @Version 1.0
 */
public interface RedPackService {
    //id就当是发送者的id
    RedPack getRedPack(String id);
    //返回redis队列的key
    String produce(String id);
}
