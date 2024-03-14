package com.example.medicalservice.service.jobs;

import com.example.medicalservice.model.entity.Patient;
import com.example.medicalservice.service.service.BureaucracyService;
import com.example.medicalservice.service.service.PatientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RefreshPatientInfoJob implements Job {

    private final PatientService patientService;
    private final BureaucracyService bureaucracyService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        var unregisteredPatients = patientService.getAllWithNoCitizenId();

        log.info("RefreshPatientInfoJob started for patients: {}", unregisteredPatients.stream().map(Patient::getId).toList());

        unregisteredPatients.forEach(patient -> {
            Long citizenId = bureaucracyService.getCitizenIdForPatient(patient);
            patient.setCitizenId(citizenId);
        });

        patientService.saveAll(unregisteredPatients);

        log.info("RefreshPatientInfoJob completed");
    }
}
