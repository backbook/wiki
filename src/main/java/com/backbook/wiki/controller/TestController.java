package com.backbook.wiki.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    //获取application.properties的变量值，如果拿不到则为默认值
    @Value("${test.hello:TEST}")
    private String testValue;

    @GetMapping("/hello")
    public String hello(){
        return "hello world " + testValue;
    }

    @PostMapping("/hello/post")
    public String helloPost(String name){
        return "hello world! Post, "+name;
    }

    @GetMapping("/")
    public String wiki(){
        return "welcome to wiki";
    }


}
