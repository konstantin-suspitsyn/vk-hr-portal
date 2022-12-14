package ru.suspitsyn.work.black.list;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.suspitsyn.work.black.list.entity.BlackList;
import ru.suspitsyn.work.black.list.service.BlackListService;
import ru.suspitsyn.work.feign.black.list.BlackListIsBlocked;

@Slf4j
@RestController
@RequestMapping("api/v1/black-list")
public class BlackListController {

    @Autowired
    BlackListService blackListService;

    @GetMapping("/is_blocked")
    public BlackListIsBlocked checkInBlackList(@RequestParam Long userId) {
        BlackList blackList = blackListService.createBlackListEntityIfNotExists(userId);
        log.info("Проверен пользователь {}", userId);
        return new BlackListIsBlocked(blackList.getIsBlocked());
    }

}
