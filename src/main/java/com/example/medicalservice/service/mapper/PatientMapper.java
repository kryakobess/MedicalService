package com.example.medicalservice.service.mapper;

import com.example.medicalservice.model.dto.CitizenInfoRequestDto;
import com.example.medicalservice.model.dto.PatientDto;
import com.example.medicalservice.model.entity.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    @Mapping(target = "documentType", constant = "IDENTITY_PASSPORT")
    CitizenInfoRequestDto toCitizenInfoRequestDto(Patient patient);

    PatientDto toDto(Patient entity);
}
