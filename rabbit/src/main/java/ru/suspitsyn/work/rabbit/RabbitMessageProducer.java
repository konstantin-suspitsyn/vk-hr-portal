package ru.suspitsyn.work.rabbit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RabbitMessageProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(Object payload, String exchange, String routingKey) {

        log.info("Отправляю в {} используя {} сообщение", exchange, routingKey, payload);
        amqpTemplate.convertAndSend(exchange, routingKey, payload);
        log.info("Отправлено в {} используя {} сообщение", exchange, routingKey, payload);

    }

}
