package ru.suspitsyn.work.rabbit;

import lombok.Getter;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class RabbitConfigurationVKTopic {
    private String internalExchange = "internal.vk";

    private String notificationQueue = "notification.vk";

    private String internalNotificationRoutingKey = "internal.notification.routing.vk";

    @Bean
    public TopicExchange topicVkExchange() {
        return new TopicExchange(this.internalExchange);
    }

    @Bean
    public Binding internalVkToNotificationBinding() {
        return BindingBuilder
                .bind(notificationVkQueue())
                .to(topicVkExchange())
                .with(this.internalNotificationRoutingKey);
    }

    @Bean
    public Queue notificationVkQueue() {
        return new Queue(this.notificationQueue);
    }

}
