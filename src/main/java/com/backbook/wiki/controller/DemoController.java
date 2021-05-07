package com.backbook.wiki.controller;

import com.backbook.wiki.domain.Demo;
import com.backbook.wiki.service.DemoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Resource
    private DemoService demoService;

    @GetMapping("/list")
    public Demo list(int id){
        return demoService.list(id);
    }


}
