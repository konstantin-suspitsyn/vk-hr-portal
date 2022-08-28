# vk-hr-portal
Pet проект, тренировка микросервисов

![Схема](https://github.com/konstantin-suspitsyn/vk-hr-portal/blob/main/assets/vk_microservices.drawio.png)


## Как сделать, чтобы заработало
Создать файл в 
```
package ru.suspitsyn.work.vk;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@Getter
@Setter
@ConfigurationProperties(prefix = "search")
public class VkConfig {
    // Добавить APP_ID
    private final Integer APP_ID = APP_ID;
    // Добавить SECRET_KEY
    private final String SECRET_KEY = "SECRET_KEY";

    private List<String> searchWords;


}
```
