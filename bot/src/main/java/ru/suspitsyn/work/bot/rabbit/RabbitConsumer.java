package ru.suspitsyn.work.bot.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.suspitsyn.work.bot.BotService;
import ru.suspitsyn.work.custom.job.entity.CustomJob;
import ru.suspitsyn.work.rabbit.RabbitConfigurationWorkTopic;
import ru.suspitsyn.work.vk.rabbit.WorkFromVk;

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
    public void consumeCustomJob(CustomJob customJobRecord) {
        botService.sendCustomJob(customJobRecord);
    }

    @RabbitListener(queues = "${rabbitmq.exchanges.queue.vknotification}")
    public void consumeVkJob(WorkFromVk workFomVk) {
        botService.sendVkJob(workFomVk);
    }

}
