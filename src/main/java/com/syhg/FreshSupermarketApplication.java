package com.syhg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.syhg.mapper")
public class FreshSupermarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(FreshSupermarketApplication.class, args);
    }

}
