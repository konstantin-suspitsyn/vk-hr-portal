package ru.suspitsyn.work.bot;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

public record CustomJobRecord (
    String vacancyText, String city, String link, Integer moneyOfferFrom, Integer moneyOfferTo, Long userId) {
}
