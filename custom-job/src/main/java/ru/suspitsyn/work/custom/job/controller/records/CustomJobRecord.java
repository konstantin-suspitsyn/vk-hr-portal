package ru.suspitsyn.work.custom.job.controller.records;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@Setter
public class CustomJobRecord {
    private String vacancyText;
    private String city;
    private String link;
    private Integer moneyOfferFrom;
    private Integer moneyOfferTo;
    private Long userId;
}
