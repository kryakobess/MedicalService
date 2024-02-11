package com.example.medicalservice.service.service.impl;

import com.example.medicalservice.model.entity.Patient;
import com.example.medicalservice.service.client.BureaucracyClient;
import com.example.medicalservice.service.mapper.PatientMapper;
import com.example.medicalservice.service.service.BureaucracyService;
import feign.FeignException;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BureaucracyServiceImpl implements BureaucracyService {

    private final BureaucracyClient bureaucracyClient;
    private final PatientMapper patientMapper;

    @Override
    @Nullable
    public Long getCitizenIdForPatient(Patient patient) {
        try {
            log.info("Checking for patient: {} {} in bureaucracy service", patient.getFirstName(), patient.getSecondName());
            var response = bureaucracyClient.getByNameAndDocument(patientMapper.toCitizenInfoRequestDto(patient));
            log.info("Found id for Patient {} {}, citizen_id={}", patient.getFirstName(), patient.getSecondName(), response.getCitizenId());
            return response.getCitizenId();
        } catch (FeignException e) {
            var response = e.contentUTF8();
            log.warn("Failed to fetch citizen id for patient with firstname:{}, secondname: {}. Response: {}. Mark as unknown",
                    patient.getFirstName(), patient.getSecondName(), response);
        }
        return null;
    }
}
