package com.example.demo.seckill.intercepter;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: chensai
 * @Date: 2019/1/11 16:27
 * @Version 1.0
 */
@Component
public class OneInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("我就是拦截一下");
        return true;
    }
}
