package com.example.medicalservice.service.service;

import com.example.medicalservice.model.entity.Patient;

public interface BureaucracyService {
    Long getCitizenIdForPatient(Patient patient);
}
