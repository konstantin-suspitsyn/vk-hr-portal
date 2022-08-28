package ru.suspitsyn.work.vk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.Arrays;

@SpringBootApplication(scanBasePackages = {"ru.suspitsyn.work.vk", "ru.suspitsyn.work.rabbit"})
@EnableEurekaClient
@EnableFeignClients(basePackages = "ru.suspitsyn.work.feign")
public class VkApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(VkApplication.class, args);
    }

    @Autowired
    private VkConfig vkConfig;

    @Autowired
    VkStreamRunner vkStreamRunner;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(Arrays.toString(vkConfig.getSearchWords().toArray()));
        vkStreamRunner.getDataFromVk();
    }
}
