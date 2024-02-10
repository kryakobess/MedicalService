package com.example.medicalservice.service.facade.place;

import com.example.medicalservice.model.dto.VaccinationPlaceDto;
import com.example.medicalservice.service.mapper.VaccinationPlaceMapper;
import com.example.medicalservice.service.service.VaccinationPlaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class VaccinationPlaceFacadeImpl implements VaccinationPlaceFacade {

    private final VaccinationPlaceService vaccinationPlaceService;
    private final VaccinationPlaceMapper vaccinationPlaceMapper;

    @Override
    public Page<VaccinationPlaceDto> getVaccinationPlaces(Pageable pageable) {
        log.info("Getting GET getVaccinationPlaces request. Pageable: {}", pageable);
        return vaccinationPlaceService.getVaccinationPlacesPageable(pageable).map(vaccinationPlaceMapper::toDto);
    }
}
