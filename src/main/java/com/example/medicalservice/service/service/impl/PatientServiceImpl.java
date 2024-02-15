package com.example.medicalservice.service.service.impl;

import com.example.medicalservice.model.entity.Patient;
import com.example.medicalservice.repository.PatientRepository;
import com.example.medicalservice.service.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    @Override
    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public List<Patient> getByDocumentNumbers(List<String> documentNumbers) {
        return patientRepository.getAllByDocumentNumberIn(documentNumbers);
    }

    @Override
    public List<Patient> getAllWithNoCitizenId() {
        return patientRepository.findAllByCitizenIdIsNull();
    }

    @Override
    public void saveAll(List<Patient> patients) {
        patientRepository.saveAll(patients);
    }
}
