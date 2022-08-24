package ru.suspitsyn.work.custom.job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients(basePackages = "ru.suspitsyn.work.feign")
@EnableEurekaClient
public class CustomJobApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomJobApplication.class, args);
    }
}
