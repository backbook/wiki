package com.backbook.wiki.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/hello")
    public String hello(){
        return "hello world";
    }

    @GetMapping("/")
    public String wiki(){
        return "welcome to wiki";
    }

}