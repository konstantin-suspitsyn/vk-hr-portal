package ru.suspitsyn.work.bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.suspitsyn.work.bot.rabbit.record.CustomJobRecord;
import ru.suspitsyn.work.bot.rabbit.record.VkWorkRecord;

@Service
@Slf4j
public class BotService {
    public void sendCustomJob(CustomJobRecord customJobRecord) {
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

    public void sendVkJob(VkWorkRecord workFomVk) {
        log.info("Загружено инфо из VK Strem. Текст: {}, link: {}", workFomVk.getText(), workFomVk.getLink());
    }
}
