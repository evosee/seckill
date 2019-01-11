package com.example.demo.seckill.service.impl;

import com.example.demo.seckill.bean.OneBean;
import com.example.demo.seckill.dao.OneDao;
import com.example.demo.seckill.service.OneService;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: chensai
 * @Date: 2019/1/11 10:55
 * @Version 1.0
 */
@Service
public class OneServiceImpl implements OneService {
    @Autowired
    private OneDao oneDao;


    @Override
    public void save(OneBean oneBean) {
        oneDao.save(oneBean);
    }

    @Override
    @Transactional
    public void noTrans(OneBean oneBean) {
        //如果获取的不是代理类 则不会回滚因为spring是用的代理实现
      //  OneService oneService = (OneService) AopContext.currentProxy();
        oneDao.save(oneBean);
        int i = 1/0;
    }
}
