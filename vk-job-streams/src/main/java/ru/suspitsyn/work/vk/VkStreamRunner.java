package ru.suspitsyn.work.vk;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.ServiceActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.streaming.responses.GetServerUrlResponse;
import com.vk.api.sdk.streaming.clients.StreamingEventHandler;
import com.vk.api.sdk.streaming.clients.VkStreamingApiClient;
import com.vk.api.sdk.streaming.clients.actors.StreamingActor;
import com.vk.api.sdk.streaming.exceptions.StreamingApiException;
import com.vk.api.sdk.streaming.exceptions.StreamingClientException;
import com.vk.api.sdk.streaming.objects.StreamingCallbackMessage;
import com.vk.api.sdk.streaming.objects.StreamingRule;
import com.vk.api.sdk.streaming.objects.responses.StreamingGetRulesResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.suspitsyn.work.rabbit.RabbitConfigurationVKTopic;
import ru.suspitsyn.work.rabbit.RabbitMessageProducer;
import ru.suspitsyn.work.vk.rabbit.WorkFromVk;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;

@Component
@Slf4j
public class VkStreamRunner {

    private final VkConfig vkToKafkaSecretKeys;
    //Create service actor
    private final Integer appId;
    private final String accessToken;

    //Init clients
    private final TransportClient transportClient;
    private final VkApiClient vkClient;
    private final VkStreamingApiClient streamingClient;
    //Get streaming actor
    private final GetServerUrlResponse getServerUrlResponse;
    private final StreamingActor actor;

    private final RabbitMessageProducer rabbitMessageProducer;
    private final RabbitConfigurationVKTopic rabbitConfigurationVKTopic;

    public VkStreamRunner(VkConfig vkToKafkaSecretKeys, RabbitMessageProducer rabbitMessageProducer, RabbitConfigurationVKTopic rabbitConfigurationVKTopic) throws ClientException, ApiException, StreamingClientException, StreamingApiException {
        this.vkToKafkaSecretKeys = vkToKafkaSecretKeys;
        this.rabbitMessageProducer = rabbitMessageProducer;
        this.rabbitConfigurationVKTopic = rabbitConfigurationVKTopic;
        this.appId = this.vkToKafkaSecretKeys.getAPP_ID();
        this.accessToken = this.vkToKafkaSecretKeys.getSECRET_KEY();

        this.transportClient = new HttpTransportClient();
        this.vkClient = new VkApiClient(this.transportClient);
        this.streamingClient = new VkStreamingApiClient(transportClient);
        this.getServerUrlResponse  = vkClient.streaming().getServerUrl(new ServiceActor(appId, accessToken)).execute();
        this.actor = new StreamingActor(getServerUrlResponse.getEndpoint(), getServerUrlResponse.getKey());

        StreamingGetRulesResponse response = streamingClient.rules().get(actor).execute();

        deleteAndCreateTags(response);

        response = streamingClient.rules().get(actor).execute();
    }

    /**
     * Удаляет все правила, что есть и вставляет актуальные из application.yml
     */
    private void deleteAndCreateTags(StreamingGetRulesResponse streamingGetRulesResponse) throws StreamingClientException, StreamingApiException {

        Set<String> vkKeyWordsSet = new HashSet<>();

        for (String key:
                vkToKafkaSecretKeys.getSearchWords()) {
            vkKeyWordsSet.add(key);
        }

        for (StreamingRule streamingRule:
                streamingGetRulesResponse.getRules()) {
            if (vkKeyWordsSet.contains(streamingRule.getValue())) {
                // Delete from Set of KeyWord existing word
                vkKeyWordsSet.remove(streamingRule.getValue());
            } else {
                log.info("Tag: {}", streamingRule.getTag());
                System.out.println(streamingRule.getTag());

                try {
                    streamingClient.rules().delete(actor,String.valueOf(streamingRule.getTag())).execute();
                } catch (StreamingClientException e) {
                    log.info(String.valueOf(e));
                } catch (StreamingApiException e) {
                    log.info(String.valueOf(e));
                }
            }
        }

        if (!vkKeyWordsSet.isEmpty()) {
            for (String vkKey:
                    vkKeyWordsSet) {
                streamingClient.rules().add(actor, vkKey, vkKey).execute();
            }
        }

    }

    public void getDataFromVk() throws ExecutionException, InterruptedException {
        streamingClient.stream().get(
                actor, new StreamingEventHandler() {
                    @Override
                    public void handle(StreamingCallbackMessage streamingCallbackMessage) {
                        log.info("Что-то пришло {}", streamingCallbackMessage);
                        WorkFromVk work = new WorkFromVk(streamingCallbackMessage.getEvent().getText(), streamingCallbackMessage.getEvent().getEventUrl());
                        rabbitMessageProducer.send(work, rabbitConfigurationVKTopic.getInternalExchange(), rabbitConfigurationVKTopic.getInternalNotificationRoutingKey());
                        log.info("{} отправлено в {} по ключу {}", work, rabbitConfigurationVKTopic.getInternalExchange(), rabbitConfigurationVKTopic.getInternalNotificationRoutingKey());
                    }

            }
        ).execute();
    }


}
