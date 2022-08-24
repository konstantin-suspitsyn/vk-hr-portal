package ru.suspitsyn.work.feign.black.list;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "black-list")
public interface BlackListClient {

    @GetMapping("api/v1/black-list/is_blocked?userId=")
    BlackListIsBlocked checkInBlackList(@RequestParam Long userId);
}
