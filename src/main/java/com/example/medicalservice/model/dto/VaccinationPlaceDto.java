package com.example.medicalservice.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VaccinationPlaceDto {
    private Integer id;
    private String organizationName;
    private String divisionName;
}
