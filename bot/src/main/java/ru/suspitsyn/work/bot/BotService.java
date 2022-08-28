package ru.suspitsyn.work.bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.suspitsyn.work.custom.job.entity.CustomJob;
import ru.suspitsyn.work.vk.rabbit.WorkFromVk;

@Service
@Slf4j
public class BotService {
    public void sendCustomJob(CustomJob customJobRecord) {
        log.info("Загружено инфо из Custom Job: vacancyText: {}; " +
                "city: {}; " +
                "link: {}; " +
                "moneyOfferFrom: {}; " +
                "moneyOfferTo: {}; " +
                "userId: {} ",
                customJobRecord.getVacancyText(),
                customJobRecord.getCity(),
                customJobRecord.getLink(),
                customJobRecord.getMoneyOfferFrom(),
                customJobRecord.getMoneyOfferTo(),
                customJobRecord.getUserId()
                );
    }

    public void sendVkJob(WorkFromVk workFomVk) {
        log.info("Загружено инфо из VK Strem. Текст: {}, link: {}", workFomVk.getText(), workFomVk.getLink());
    }
}
