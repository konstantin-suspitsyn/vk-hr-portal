package ru.suspitsyn.work.custom.job.service;

import ru.suspitsyn.work.custom.job.controller.records.CustomJobRecord;

public interface CustomJobService {
    void createCustomJob(CustomJobRecord customJobRecord);
}
