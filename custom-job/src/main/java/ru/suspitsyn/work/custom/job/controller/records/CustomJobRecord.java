package ru.suspitsyn.work.custom.job.controller.records;

public record CustomJobRecord (
    String vacancyText,
    String city,
    String link,
    Integer moneyOfferFrom,
    Integer moneyOfferTo,
    String company) {
}
