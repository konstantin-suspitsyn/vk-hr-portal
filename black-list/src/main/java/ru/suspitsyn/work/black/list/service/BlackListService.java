package ru.suspitsyn.work.black.list.service;

import ru.suspitsyn.work.black.list.entity.BlackList;

public interface BlackListService {

    BlackList createBlackListEntityIfNotExists(Long userId);
    void addLike(Long userId, Long like);
    void addDislike(Long userId, Long dislikes);
    BlackList checkEnoughGrades(BlackList blackList);
    BlackList setBlocked(BlackList blackList);

    BlackList checkAndSetGradesAndBlocked(BlackList blackList);


}
