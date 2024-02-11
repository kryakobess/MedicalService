package com.example.medicalservice.repository;

import com.example.medicalservice.model.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> getAllByDocumentNumberIn(List<String> documentNumbers);

    List<Patient> findAllByCitizenIdIsNull();
}
