package com.edu.edufileboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EduFileBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduFileBootApplication.class, args);
    }

}
