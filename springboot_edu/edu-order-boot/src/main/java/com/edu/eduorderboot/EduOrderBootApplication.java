package com.edu.eduorderboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient // 注册到中心的客户端
@MapperScan("com.edu.eduorderboot.mapper")  // 扫描mapper包
public class EduOrderBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduOrderBootApplication.class, args);
    }

}
