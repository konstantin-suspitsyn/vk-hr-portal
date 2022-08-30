package ru.suspitsyn.work.bot.rabbit.record;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomJobRecord {
    private Long id;
    private String vacancyText;
    private String city;
    private String link;
    private Integer moneyOfferFrom;
    private Integer moneyOfferTo;
    private Long userId;
}
