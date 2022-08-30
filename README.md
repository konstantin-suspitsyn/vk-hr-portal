# vk-hr-portal
Pet проект, тренировка микросервисов

![Схема](https://github.com/konstantin-suspitsyn/vk-hr-portal/blob/main/assets/vk_microservices.drawio.png)

custom-job слушает POST-Request (порт 8080) вида

```
{
    "vacancyText":"Текст тестовой вакансии",
    "city":"Москва",
    "link":"href=//123.com",
    "moneyOfferFrom":1000,
    "moneyOfferTo":9000,
    "userId":61
}
```
Записывает вакансии в Postgres БД и отправляет GET-запрос в сервис black-list

Сервис black-list проверяет userId в своей БД и отвечает сервису custom-job

При положительном ответе, custom-job отправляет сообщение в RabbitMQ

Адреса обмена данными регистрируется в сервисе eurika-server, а API запросов проходят через feign-client

Zipkin следит за тем как бегают запросы

vk-job-streams, используя VK Stream API, получает сообщения о работе и направляет сообщения в RabbitMQ

bot читает потоки данных из RabbitMQ и записывает их в sl4j


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
