package com.example.medicalservice.service.service.impl;

import com.example.medicalservice.model.entity.VaccinationPlace;
import com.example.medicalservice.repository.VaccinationPlaceRepository;
import com.example.medicalservice.service.service.VaccinationPlaceService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class VaccinationPlaceServiceImpl implements VaccinationPlaceService {

    private final VaccinationPlaceRepository vaccinationPlaceRepository;

    @Override
    public List<VaccinationPlace> getByOrganizationNames(List<String> organizationNames) {
        return vaccinationPlaceRepository.findAllByOrganizationNameIn(organizationNames);
    }

    @Override
    public VaccinationPlace save(VaccinationPlace vaccinationPlace) {
        return vaccinationPlaceRepository.save(vaccinationPlace);
    }

    @Override
    public Page<VaccinationPlace> getVaccinationPlacesPageable(Pageable pageable) {
        return vaccinationPlaceRepository.findAll(pageable);
    }

}
