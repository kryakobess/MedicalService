package com.example.medicalservice.model.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthJwtRequestDto {
    private String username;
    private String password;
}
