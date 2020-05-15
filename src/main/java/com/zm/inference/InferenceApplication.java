package com.zm.inference;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zm.inference")
public class InferenceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InferenceApplication.class, args);
    }

}
