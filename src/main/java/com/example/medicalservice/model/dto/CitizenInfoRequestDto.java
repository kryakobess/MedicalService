package com.example.medicalservice.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CitizenInfoRequestDto {
    private String firstName;
    private String secondName;
    private String documentType;
    private String documentNumber;
}
