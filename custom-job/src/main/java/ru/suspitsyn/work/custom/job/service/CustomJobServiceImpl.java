package ru.suspitsyn.work.custom.job.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.suspitsyn.work.custom.job.config.CustomJobConfig;
import ru.suspitsyn.work.custom.job.controller.records.CustomJobRecord;
import ru.suspitsyn.work.custom.job.entity.CustomJob;
import ru.suspitsyn.work.custom.job.entity.CustomJobRepository;
import ru.suspitsyn.work.feign.black.list.BlackListClient;
import ru.suspitsyn.work.feign.black.list.BlackListIsBlocked;

@Service
public class CustomJobServiceImpl implements CustomJobService {

    @Autowired
    private CustomJobRepository customJobRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private BlackListClient blackListClient;
    public CustomJob saveCustomJob(CustomJob customJob) {
        return customJobRepository.save(customJob);
    }

    @Override
    public void createCustomJob(CustomJobRecord customJobRecord) {

        CustomJob customJob = new CustomJob();
        customJob.setCity(customJobRecord.getCity());
        customJob.setUserId(customJobRecord.getUserId());
        customJob.setLink(customJobRecord.getLink());
        customJob.setVacancyText(customJobRecord.getVacancyText());
        customJob.setMoneyOfferFrom(Integer.valueOf(customJobRecord.getMoneyOfferFrom()));
        customJob.setMoneyOfferTo(Integer.valueOf(customJobRecord.getMoneyOfferTo()));
        customJob = saveCustomJob(customJob);


         BlackListIsBlocked blackListIsBlocked = blackListClient.checkInBlackList(customJob.getUserId());

        if(blackListIsBlocked.isBlocked()==true) {
            //TODO: send to message Queue or not
        }

    }
}
