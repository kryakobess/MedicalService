package com.example.medicalservice.configuration.quartz;

import com.example.medicalservice.service.jobs.RefreshPatientInfoJob;
import lombok.RequiredArgsConstructor;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RefreshPatientInfoJobConfig {

    private final SchedulerProperties schedulerProperties;
    private static final String REFRESH_PATIENT_JOB_DETAIL = "refreshPatientJobDetail";
    private static final String REFRESH_PATIENT_TRIGGER = "refreshPatientTrigger";

    @Bean
    public JobDetail refreshPatientInfoJobDetail() {
        return JobBuilder.newJob(RefreshPatientInfoJob.class)
                .withIdentity(REFRESH_PATIENT_JOB_DETAIL)
                .storeDurably()
                .requestRecovery()
                .build();
    }

    @Bean
    public Trigger refreshPatientInfoTrigger() {
        return TriggerBuilder.newTrigger()
                .forJob(refreshPatientInfoJobDetail())
                .withIdentity(REFRESH_PATIENT_TRIGGER)
                .withSchedule(CronScheduleBuilder.cronSchedule(schedulerProperties.getRefreshPatientInfoJobCron()))
                .build();
    }
}
