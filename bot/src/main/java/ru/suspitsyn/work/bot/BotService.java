package ru.suspitsyn.work.bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.suspitsyn.work.custom.job.controller.records.CustomJobRecord;
import ru.suspitsyn.work.custom.job.entity.CustomJob;

@Service
@Slf4j
public class BotService {
    public void send(CustomJob customJobRecord) {
        log.info("Загружено инфо: vacancyText: {}; " +
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
}
