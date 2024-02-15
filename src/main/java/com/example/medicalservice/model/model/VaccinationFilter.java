package com.example.medicalservice.model.model;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VaccinationFilter {
    List<Long> ids;
    List<String> vaccineTypes;
    LocalDate dateFrom;
    LocalDate dateUntil;
    List<String> organizationNames;
    List<String> divisionNames;
    List<String> patientFirstNames;
    List<String> patientSecondNames;
}
