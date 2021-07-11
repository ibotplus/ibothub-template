package com.ibothub.love.template;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ibothub.love.template.dao")
public class IbothubTemplateApplication {

    public static void main(String[] args) {
        System.setProperty("appName", IbothubTemplateApplication.class.getSimpleName());
        SpringApplication.run(IbothubTemplateApplication.class, args);
    }

}
