package com.example.demo.seckill.test;

import com.example.demo.seckill.bean.OneBean;
import com.example.demo.seckill.service.OneService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: chensai
 * @Date: 2019/1/11 11:00
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TransTest {
    @Autowired
    private OneService oneService;
    @Test
    public void test(){
        OneBean oneBean = new OneBean("1");
        oneService.save(oneBean);
    }

    @Test
    public void testNoTrans(){
        OneBean oneBean = new OneBean("3");
        oneService.noTrans(oneBean);
    }

}
