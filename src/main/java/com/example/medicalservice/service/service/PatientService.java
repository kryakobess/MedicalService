package com.example.medicalservice.service.service;

import com.example.medicalservice.model.entity.Patient;

import java.util.List;

public interface PatientService {
    Patient save(Patient patient);
    List<Patient> getByDocumentNumbers(List<String> documentNumbers);
    List<Patient> getAllWithNoCitizenId();

    void saveAll(List<Patient> patients);
}
