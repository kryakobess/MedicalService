package com.example.medicalservice.configuration.quartz;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("scheduler")
@Getter
@Setter
public class SchedulerProperties {
    private String refreshPatientInfoJobCron;
}
