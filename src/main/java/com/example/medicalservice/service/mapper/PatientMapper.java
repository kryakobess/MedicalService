package com.example.medicalservice.service.mapper;

import com.example.medicalservice.model.dto.CitizenInfoRequestDto;
import com.example.medicalservice.model.model.Patient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    CitizenInfoRequestDto toCitizenInfoRequestDto(Patient patient);
}
