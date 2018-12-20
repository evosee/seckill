package com.example.demo.seckill.bean;

/**
 * @Author: chensai
 * @Date: 2018/12/20 17:13
 * @Version 1.0
 */
public class RedPack {
    public RedPack(String redPackId, String money) {
        this.redPackId = redPackId;
        this.money = money;
    }

    private String redPackId;
    private String money;

    public String getRedPackId() {
        return redPackId;
    }

    public void setRedPackId(String redPackId) {
        this.redPackId = redPackId;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
