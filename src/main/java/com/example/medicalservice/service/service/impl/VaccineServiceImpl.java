package com.example.medicalservice.service.service.impl;

import com.example.medicalservice.model.entity.VaccinationPlace;
import com.example.medicalservice.model.entity.Vaccine;
import com.example.medicalservice.model.model.Patient;
import com.example.medicalservice.model.model.ReportData;
import com.example.medicalservice.repository.VaccineRepository;
import com.example.medicalservice.service.VaccineMapper;
import com.example.medicalservice.service.client.BureaucracyClient;
import com.example.medicalservice.service.mapper.PatientMapper;
import com.example.medicalservice.service.service.VaccinationPlaceService;
import com.example.medicalservice.service.service.VaccineService;
import com.example.medicalservice.service.service.VaccineTypeService;
import feign.FeignException;
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
    private final BureaucracyClient bureaucracyClient;
    private final VaccinationPlaceService vaccinationPlaceService;
    private final VaccineTypeService vaccineTypeService;
    private final VaccineRepository vaccineRepository;
    private final PatientMapper patientMapper;

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
        vaccines.forEach(vaccine -> {
            var patient = vaccine.getPatient();
            Long citizenId = getCitizenId(patient);
            vaccine.setCitizenId(citizenId);
        });
    }

    private Long getCitizenId(Patient patient) {
        try {
            log.info("Checking for patient: {} {} in bureaucracy service", patient.getFirstName(), patient.getSecondName());
            var response = bureaucracyClient.getByNameAndDocument(patientMapper.toCitizenInfoRequestDto(patient));
            log.info("Patient {} {} is saved with id={}", patient.getFirstName(), patient.getSecondName(), response.getCitizenId());
            return response.getCitizenId();
        } catch (FeignException e) {
            log.warn("Failed to fetch citizen id for patient with firstname:{}, secondname: {}. Response: {}. Mark as unknown",
                    patient.getFirstName(), patient.getSecondName(), e.contentUTF8());
        }
        return null;
    }
}
