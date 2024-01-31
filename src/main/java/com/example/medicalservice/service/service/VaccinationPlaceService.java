package com.example.medicalservice.service.service;

import com.example.medicalservice.model.entity.VaccinationPlace;

import java.util.List;

public interface VaccinationPlaceService {
    List<VaccinationPlace> getByOrganizationNames(List<String> organizationNames);
    VaccinationPlace save(VaccinationPlace vaccinationPlace);
}
