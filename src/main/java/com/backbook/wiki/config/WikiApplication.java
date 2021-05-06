package com.backbook.wiki.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;


@SpringBootApplication
//该类会扫描类下面的同级别及其子类
@ComponentScan({"com.backbook.wiki"})
public class WikiApplication {

    private static final Logger LOG = LoggerFactory.getLogger(WikiApplication.class);
    public static void main(String[] args) {

        SpringApplication application = new SpringApplication(WikiApplication.class);
        Environment environment = application.run(args).getEnvironment();
        //添加注解用于,标识
        LOG.info("spring boot started");
        LOG.info("url:\t http://localhost:{}",environment.getProperty("server.port"));
    }


}
