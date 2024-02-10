package com.example.medicalservice.service.facade.place;

import com.example.medicalservice.model.dto.VaccinationPlaceDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VaccinationPlaceFacade {
    Page<VaccinationPlaceDto> getVaccinationPlaces(Pageable pageable);

}
