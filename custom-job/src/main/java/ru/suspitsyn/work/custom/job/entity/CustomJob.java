package ru.suspitsyn.work.custom.job.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CustomJob {

    @Transient
    private final int MAX_LINK_LENGTH = 1000;
    @Transient
    private final int MAX_TEXT_LENGTH = 800;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "vacancy_text", length = MAX_TEXT_LENGTH)
    private String vacancyText;
    @Column(name = "city")
    private String city;
    @Column(name = "link", length = MAX_LINK_LENGTH)
    private String link;
    @Column(name = "money_offer_from")
    private Integer moneyOfferFrom;
    @Column(name = "money_offer_to")
    private Integer moneyOfferTo;
    @Column(name = "company")
    private String company;

    // TODO: Сделать справочник компаний

}
