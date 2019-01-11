package com.example.demo.seckill.bean;

/**
 * @Author: chensai
 * @Date: 2019/1/11 10:52
 * @Version 1.0
 */
public class OneBean {
    private Integer id;
    private String name;

    public OneBean( String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
