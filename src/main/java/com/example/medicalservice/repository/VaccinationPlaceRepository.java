package com.example.medicalservice.repository;

import com.example.medicalservice.model.entity.VaccinationPlace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VaccinationPlaceRepository extends JpaRepository<VaccinationPlace, Integer> {
    Optional<VaccinationPlace> findByOrganizationNameAndDivisionName(String organizationName, String divisionName);
    List<VaccinationPlace> findAllByOrganizationNameIn(List<String> organizationNames);
}
