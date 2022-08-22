package ru.suspitsyn.work.custom.job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "ru.suspitsyn.work")
public class CustomJobApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomJobApplication.class, args);
    }
}
