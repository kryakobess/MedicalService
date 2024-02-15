package com.example.medicalservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VaccineDto {
    private Long id;
    private LocalDate vaccinationDate;
    private VaccineTypeDto vaccineType;
    private VaccinationPlaceDto vaccinationPlace;
    private PatientDto patient;
}
