package com.example.medicalservice.service.jobs;

import com.example.medicalservice.model.dto.VaccineQrMessageDto;
import com.example.medicalservice.model.entity.Patient;
import com.example.medicalservice.service.producer.VaccineQrCreationProducer;
import com.example.medicalservice.service.service.BureaucracyService;
import com.example.medicalservice.service.service.PatientService;
import com.example.medicalservice.service.service.VaccineService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class RefreshPatientInfoJob implements Job {

    private final PatientService patientService;
    private final BureaucracyService bureaucracyService;
    private final VaccineQrCreationProducer qrCreationProducer;
    private final VaccineService vaccineService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        var unregisteredPatients = patientService.getAllWithNoCitizenId();

        log.info("RefreshPatientInfoJob started for patients: {}", unregisteredPatients.stream().map(Patient::getId).toList());

        unregisteredPatients.forEach(patient -> {
            Long citizenId = bureaucracyService.getCitizenIdForPatient(patient);
            if (citizenId != null) {
                patient.setCitizenId(citizenId);
                patient.setDocumentNumber(null);
            }
        });

        patientService.saveAll(unregisteredPatients);

        unregisteredPatients.stream()
                .filter(patient -> patient.getCitizenId() != null)
                .forEach(this::generateQrCode);

        log.info("RefreshPatientInfoJob completed");
    }

    private void generateQrCode(Patient patient) {
        log.info("Generating qr code for patient {}", patient.getId());

        var latestVaccine = vaccineService.getLatestVaccineForPatient(patient);
        if (latestVaccine == null) {
            log.info("Patient {} does not have any data about vaccination", patient.getId());
            return;
        }
        var dateOfVaccination = latestVaccine.getVaccinationDate();
        var durationInDays = latestVaccine.getVaccineTypeEntity().getDurationInDays();

        qrCreationProducer.sendCreateQr(
                VaccineQrMessageDto.builder()
                        .citizenId(patient.getCitizenId())
                        .vaccineDurableUntil(dateOfVaccination.plusDays(durationInDays))
                        .build()
        );
    }
}
