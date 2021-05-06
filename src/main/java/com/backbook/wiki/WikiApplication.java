package com.backbook.wiki;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class WikiApplication {

    private static final Logger LOG = LoggerFactory.getLogger(WikiApplication.class);
    public static void main(String[] args) {

        SpringApplication application = new SpringApplication(WikiApplication.class);
        Environment environment = application.run(args).getEnvironment();


        LOG.info("spring boot started");
        LOG.info("url:\t http://127.0.0.0.1:{}",environment.getProperty("server.port"));
    }


}
