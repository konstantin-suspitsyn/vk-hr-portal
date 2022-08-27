package ru.suspitsyn.work.custom.job.controller.records;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@EqualsAndHashCode
@Setter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomJobRecord {
    @JsonProperty("vacancyText")
    private String vacancyText;
    @JsonProperty("city")
    private String city;
    @JsonProperty("link")
    private String link;
    @JsonProperty("moneyOfferFrom")
    private Integer moneyOfferFrom;
    @JsonProperty("moneyOfferTo")
    private Integer moneyOfferTo;
    @JsonProperty("userId")
    private Long userId;


}
