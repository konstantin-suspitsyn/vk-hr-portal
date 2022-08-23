package ru.suspitsyn.work.custom.job.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.suspitsyn.work.custom.job.controller.records.CustomJobRecord;
import ru.suspitsyn.work.custom.job.entity.CustomJob;
import ru.suspitsyn.work.custom.job.entity.CustomJobRepository;

@Service
public class CustomJobServiceImpl implements CustomJobService {

    @Autowired
    private CustomJobRepository customJobRepository;

    public CustomJob saveCustomJob(CustomJob customJob) {
        return customJobRepository.save(customJob);
    }

    @Override
    public void createCustomJob(CustomJobRecord customJobRecord) {
        CustomJob customJob = new CustomJob();
        customJob.setCity(customJobRecord.city());
        customJob.setUserId(customJobRecord.userId());
        customJob.setLink(customJobRecord.link());
        customJob.setVacancyText(customJobRecord.vacancyText());
        customJob.setMoneyOfferFrom(Integer.valueOf(customJobRecord.moneyOfferFrom()));
        customJob.setMoneyOfferTo(Integer.valueOf(customJobRecord.moneyOfferTo()));
        saveCustomJob(customJob);
    }
}
