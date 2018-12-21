package com.example.demo.seckill.web;

import com.example.demo.seckill.bean.RedPack;
import com.example.demo.seckill.service.RedPackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: chensai
 * @Date: 2018/12/20 16:51
 * @Version 1.0
 */
@RestController("/web")
public class WebController {
    @Autowired
    private RedPackService redPackService;

    @RequestMapping("/v1")
    public String getRedPack(){
        RedPack redPack =  redPackService.getRedPack("red:p:1002");
        if(redPack!=null){
            System.out.println(redPack.getMoney());
        }else System.out.println("没了哦");

        return "yes";
    }


}
