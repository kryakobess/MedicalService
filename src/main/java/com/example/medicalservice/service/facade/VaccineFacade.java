package com.example.medicalservice.service.facade;

import org.springframework.web.multipart.MultipartFile;

public interface VaccineFacade {
    void processVaccinationReport(MultipartFile report);
}
