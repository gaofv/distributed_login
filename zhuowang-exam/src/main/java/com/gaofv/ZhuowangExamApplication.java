package com.gaofv;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.gaofv.mapper")
public class ZhuowangExamApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZhuowangExamApplication.class, args);
    }

}
