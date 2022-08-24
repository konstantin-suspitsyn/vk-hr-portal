package ru.suspitsyn.work.black.list.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.suspitsyn.work.black.list.config.BlackListConfig;
import ru.suspitsyn.work.black.list.entity.BlackList;
import ru.suspitsyn.work.black.list.entity.BlackListRepository;

@Service
public class BlackListServiceImpl implements BlackListService {

    @Autowired
    private BlackListRepository blackListRepository;

    @Autowired
    private BlackListConfig blackListConfig;

    @Override
    public synchronized BlackList createBlackListEntityIfNotExists(Long userId) {
        BlackList blackList;
        if (!blackListRepository.findByUserId(userId).isPresent()) {
            blackList = new BlackList();
            blackList.setEnoughGrades(false);
            blackList.setDislike(0L);
            blackList.setLike(0L);
            blackList.setUserId(userId);
            blackList.setIsBlocked(false);
            blackList = blackListRepository.save(blackList);
        } else {
            blackList = blackListRepository.findByUserId(userId).get();
        }
        return blackList;
    }

    @Override
    public synchronized void addLike(Long userId, Long like) {
        BlackList blackList = createBlackListEntityIfNotExists(userId);
        Long likes = blackList.getLike();
        likes = likes + like;
        blackListRepository.addLike(userId, likes);
        checkAndSetGradesAndBlocked(blackList);
    }

    @Override
    public synchronized void addDislike(Long userId, Long dislike) {
        BlackList blackList = createBlackListEntityIfNotExists(userId);
        Long dislikes = blackList.getDislike();
        dislikes = dislikes + dislike;
        blackListRepository.addLike(userId, dislikes);
        checkAndSetGradesAndBlocked(blackList);
    }

    @Override
    public synchronized BlackList checkEnoughGrades(BlackList blackList) {
        if (blackList.getEnoughGrades() == false) {
            if(blackList.getDislike() + blackList.getLike() >= blackListConfig.getMinGradesNeeded()) {
                blackListRepository.setEnoughGrades(blackList.getUserId(), true);
                blackList.setEnoughGrades(true);
            }
        }
        return blackList;
    }

    @Override
    public synchronized BlackList setBlocked(BlackList blackList) {
        boolean setBlocked = false;
        if((blackList.getDislike() * 100) / (blackList.getLike() + blackList.getDislike()) >=
                blackListConfig.getPercentsToBlack()) {
            setBlocked = true;
        }
        if (blackList.getIsBlocked() != setBlocked) {
            blackList.setIsBlocked(setBlocked);
            blackListRepository.setBlocked(blackList.getUserId(), setBlocked);
        }
        return blackList;
    }

    @Override
    public synchronized BlackList checkAndSetGradesAndBlocked(BlackList blackList) {
        blackList = checkEnoughGrades(blackList);
        return setBlocked(blackList);
    }
}
