package ru.suspitsyn.work.bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BotService {
    public void send(CustomJobRecord customJobRecord) {
        log.info("Загружено инфо: vacancyText: {}; " +
                "city: {}; " +
                "link: {}; " +
                "moneyOfferFrom: {}; " +
                "moneyOfferTo: {}; " +
                "userId: {} ",
                customJobRecord.vacancyText(),
                customJobRecord.city(),
                customJobRecord.link(),
                customJobRecord.moneyOfferFrom(),
                customJobRecord.moneyOfferTo(),
                customJobRecord.userId()
                );
    }
}
