package com.example.medicalservice.service.mapper;

import com.example.medicalservice.model.dto.VaccinationPlaceDto;
import com.example.medicalservice.model.entity.VaccinationPlace;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VaccinationPlaceMapper {
    VaccinationPlaceDto toDto(VaccinationPlace entity);
}
