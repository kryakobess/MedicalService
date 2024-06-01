package com.example.medicalservice.service.service;

import com.example.medicalservice.model.entity.Patient;
import com.example.medicalservice.model.entity.Vaccine;
import com.example.medicalservice.model.model.ReportData;
import com.example.medicalservice.model.model.VaccinationFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface VaccineService {
    List<Vaccine> createFromReportData(List<ReportData> reportData);
    Page<Vaccine> getVaccinePage(VaccinationFilter filter, Pageable pageable);
    Vaccine getLatestVaccineForPatient(Patient patient);
}
