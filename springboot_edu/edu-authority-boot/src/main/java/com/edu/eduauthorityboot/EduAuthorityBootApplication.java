package com.edu.eduauthorityboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.edu.eduauthorityboot.mapper")
public class EduAuthorityBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduAuthorityBootApplication.class, args);
    }

}
