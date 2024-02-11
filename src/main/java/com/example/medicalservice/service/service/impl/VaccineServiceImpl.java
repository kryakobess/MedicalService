package com.example.medicalservice.service.service.impl;

import com.example.medicalservice.model.entity.Patient;
import com.example.medicalservice.model.entity.VaccinationPlace;
import com.example.medicalservice.model.entity.Vaccine;
import com.example.medicalservice.model.model.ReportData;
import com.example.medicalservice.repository.VaccineRepository;
import com.example.medicalservice.service.VaccineMapper;
import com.example.medicalservice.service.service.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class VaccineServiceImpl implements VaccineService {

    private final VaccineMapper vaccineMapper;
    private final VaccinationPlaceService vaccinationPlaceService;
    private final VaccineTypeService vaccineTypeService;
    private final VaccineRepository vaccineRepository;
    private final PatientService patientService;
    private final BureaucracyService bureaucracyService;

    @Override
    @Transactional
    public void createFromReportData(List<ReportData> reportData) {
        log.info("Processing reports: {}", reportData.size());
        var types = vaccineTypeService.getAll();
        List<Vaccine> vaccines = reportData.stream()
                .map(report -> vaccineMapper.fromReportData(report, types))
                .toList();
        processVaccinationPlaces(vaccines);
        processPatients(vaccines);
        vaccineRepository.saveAll(vaccines);
    }

    private void processVaccinationPlaces(List<Vaccine> vaccines) {
        var organizationNames = vaccines.stream()
                .map(vaccine -> vaccine.getVaccinationPlace().getOrganizationName())
                .toList();

        var existingPlaces = vaccinationPlaceService.getByOrganizationNames(organizationNames).stream()
                .collect(Collectors.toMap(place -> ImmutablePair.of(place.getOrganizationName(), place.getDivisionName()), Function.identity()));

        vaccines.forEach(vaccine -> {
            var place = vaccine.getVaccinationPlace();
            var key = ImmutablePair.of(place.getOrganizationName(), place.getDivisionName());
            VaccinationPlace savedPlace;
            if (!existingPlaces.containsKey(key)) {
                savedPlace = vaccinationPlaceService.save(place);
                existingPlaces.put(key, savedPlace);
            } else {
                savedPlace = existingPlaces.get(key);
            }
            vaccine.setVaccinationPlace(savedPlace);
        });
    }

    private void processPatients(List<Vaccine> vaccines) {
        var documentNumbers = vaccines.stream()
                .map(vaccine -> vaccine.getPatient().getDocumentNumber())
                .toList();

        var registeredPatients = patientService.getByDocumentNumbers(documentNumbers).stream()
                .collect(Collectors.toMap(Patient::getDocumentNumber, Function.identity()));

        vaccines.forEach(vaccine -> {
            var patient = vaccine.getPatient();
            if (registeredPatients.containsKey(patient.getDocumentNumber())) {
                patient = registeredPatients.get(patient.getDocumentNumber());
            } else {
                Long citizenId = bureaucracyService.getCitizenIdForPatient(patient);
                patient.setCitizenId(citizenId);
                patient = patientService.save(patient);
                registeredPatients.put(patient.getDocumentNumber(), patient);
            }
            vaccine.setPatient(patient);
        });
    }
}
