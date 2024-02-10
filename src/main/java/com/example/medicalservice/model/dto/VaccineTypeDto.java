package com.example.medicalservice.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VaccineTypeDto {
    private String code;
    private String description;
    private Integer durationInDays;
}
