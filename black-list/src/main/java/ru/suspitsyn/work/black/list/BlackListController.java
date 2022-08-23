package ru.suspitsyn.work.black.list;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.suspitsyn.work.black.list.entity.BlackList;
import ru.suspitsyn.work.black.list.service.BlackListService;

@RestController
@RequestMapping("api/v1/black-list")
public class BlackListController {

    @Autowired
    BlackListService blackListService;

    @GetMapping("/is_blocked")
    public boolean checkInBlackList(@RequestParam Long userId) {
        BlackList blackList = blackListService.createBlackListEntityIfNotExists(userId);
        return blackList.getIsBlocked();
    }

}
