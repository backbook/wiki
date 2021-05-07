package com.backbook.wiki.service;

import com.backbook.wiki.domain.Demo;
import com.backbook.wiki.mapper.DemoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DemoService {

    @Resource
    private DemoMapper demoMapper;


    public Demo list(int id){
        return demoMapper.selectByPrimaryKey(id);
    }

}
