package com.withmes;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(proxyBeanMethods = false)
@MapperScan(basePackages = "com.withmes.*.mapper", sqlSessionTemplateRef = "sqlSessionTemplate")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


}
