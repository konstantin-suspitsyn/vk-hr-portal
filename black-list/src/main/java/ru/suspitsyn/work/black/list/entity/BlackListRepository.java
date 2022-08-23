package ru.suspitsyn.work.black.list.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

public interface BlackListRepository extends JpaRepository<BlackList, Long> {

    @Transactional
    @Query("UPDATE BlackList b " +
            "SET b.like =?2 " +
            "WHERE b.userId = ?1")
    void addLike(Long userId, Long like);

    @Transactional
    @Query("UPDATE BlackList b " +
            "SET b.dislike =?2 " +
            "WHERE b.userId = ?1")
    void addDislike(Long userId, Long dislikes);

    @Transactional
    @Query("UPDATE BlackList b " +
            "SET b.enoughGrades =?2 " +
            "WHERE b.userId = ?1")
    void setEnoughGrades(Long userId, Boolean enoughGrades);

    @Transactional
    @Query("UPDATE BlackList b " +
            "SET b.isBlocked =?2 " +
            "WHERE b.userId = ?1")
    void setBlocked(Long userId, Boolean isBlocked);

    @Query("SELECT b " +
            "FROM BlackList b " +
            "WHERE b.userId = ?1")
    Optional<BlackList> findByUserId(Long userId);

}
