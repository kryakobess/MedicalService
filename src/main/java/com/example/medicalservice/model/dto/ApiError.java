package com.example.medicalservice.model.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ApiError {
    private String message;
    private String reason;
}
