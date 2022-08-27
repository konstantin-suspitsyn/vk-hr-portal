package ru.suspitsyn.work.bot.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.suspitsyn.work.bot.BotService;
import ru.suspitsyn.work.bot.CustomJobRecord;
import ru.suspitsyn.work.rabbit.RabbitConfigurationWorkTopic;

@Component
public class RabbitConsumer {

    private final RabbitConfigurationWorkTopic rabbitConfigurationWorkTopic;

    private final BotService botService;

    private final String queue;

    public RabbitConsumer(RabbitConfigurationWorkTopic rabbitConfigurationWorkTopic, BotService botService) {
        this.rabbitConfigurationWorkTopic = rabbitConfigurationWorkTopic;
        this.botService = botService;
        this.queue = rabbitConfigurationWorkTopic.getNotificationQueue();
    }

    @RabbitListener(queues = "${rabbitmq.exchanges.queue.notification}")
    public void consume(CustomJobRecord customJobRecord) {
        botService.send(customJobRecord);
    }

}
