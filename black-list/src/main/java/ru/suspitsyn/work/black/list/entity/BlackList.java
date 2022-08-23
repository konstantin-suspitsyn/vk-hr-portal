package ru.suspitsyn.work.black.list.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BlackList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "like")
    private Long like;

    @Column(name = "dislike")
    private Long dislike;

    @Column(name = "enough_grades")
    private Boolean enoughGrades;

    @Column(name = "is_blocked")
    private Boolean isBlocked;
}
