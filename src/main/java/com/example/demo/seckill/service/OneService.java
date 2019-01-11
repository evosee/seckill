package com.example.demo.seckill.service;

import com.example.demo.seckill.bean.OneBean;

/**
 * @Author: chensai
 * @Date: 2019/1/11 10:54
 * @Version 1.0
 */
public interface OneService {
    void save(OneBean oneBean);
    void noTrans(OneBean oneBean);
}
