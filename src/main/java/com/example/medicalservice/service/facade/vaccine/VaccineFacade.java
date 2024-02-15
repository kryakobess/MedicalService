package com.example.medicalservice.service.facade.vaccine;

import com.example.medicalservice.model.dto.VaccineDto;
import com.example.medicalservice.model.model.VaccinationFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VaccineFacade {
    List<VaccineDto> processVaccinationReport(MultipartFile report);

    Page<VaccineDto> getVaccinationInfoPage(VaccinationFilter filter, Pageable pageable);
}
