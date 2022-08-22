package ru.suspitsyn.work.custom.job.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.suspitsyn.work.custom.job.controller.records.CustomJobRecord;
import ru.suspitsyn.work.custom.job.service.CustomJobService;

@Slf4j
@RestController
@RequestMapping("api/v1/custom_job")
@AllArgsConstructor
public class CustomJobController {

    @Autowired
    private final CustomJobService customJobService;

    @PostMapping
    public void createCustomJob(@RequestBody CustomJobRecord customJobRecord) {
        customJobService.createCustomJob(customJobRecord);
    }

}
