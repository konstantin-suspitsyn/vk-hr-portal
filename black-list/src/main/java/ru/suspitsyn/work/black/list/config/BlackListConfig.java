package ru.suspitsyn.work.black.list.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "blacklist-config")
public class BlackListConfig {

    private Integer minGradesNeeded;
    private Integer percentsToBlack;

}
