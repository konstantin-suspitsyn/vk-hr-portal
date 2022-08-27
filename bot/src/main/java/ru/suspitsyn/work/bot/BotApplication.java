package ru.suspitsyn.work.bot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(scanBasePackages= {"ru.suspitsyn.work.rabbit", "ru.suspitsyn.work.bot"}, exclude = {DataSourceAutoConfiguration.class })
public class BotApplication {
    public static void main(String[] args) {
        SpringApplication.run(BotApplication.class, args);
    }

}
