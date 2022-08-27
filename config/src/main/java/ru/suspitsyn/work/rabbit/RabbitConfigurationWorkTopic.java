package ru.suspitsyn.work.rabbit;

import lombok.Getter;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class RabbitConfigurationWorkTopic {
//    @Value("${rabbitmq.exchanges.internal}")
    private String internalExchange = "internal.work";

//    @Value("${rabbitmq.exchanges.queue.notification}")
    private String notificationQueue = "notification.work";

//    @Value("${rabbitmq.exchanges.routing-keys.internal-notification}")
    private String internalNotificationRoutingKey = "internal.notification.routing.work";

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(this.internalExchange);
    }

    @Bean
    public Binding internalToNotificationBinding() {
        return BindingBuilder
                .bind(notificationQueue())
                .to(topicExchange())
                .with(this.internalNotificationRoutingKey);
    }

    @Bean
    public Queue notificationQueue() {
        return new Queue(this.notificationQueue);
    }

}
