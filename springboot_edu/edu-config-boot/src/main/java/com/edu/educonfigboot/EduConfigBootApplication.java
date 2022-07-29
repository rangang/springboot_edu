package com.edu.educonfigboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer // 配置中心
public class EduConfigBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduConfigBootApplication.class, args);
    }

}
