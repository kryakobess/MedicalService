package com.example.medicalservice.service.mapper;

import com.example.medicalservice.model.dto.VaccineTypeDto;
import com.example.medicalservice.model.entity.VaccineTypeEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VaccineTypeMapper {
    VaccineTypeDto toVaccineTypeDto(VaccineTypeEntity vaccineTypeEntity);
    List<VaccineTypeDto> toListVaccineTypeDto(List<VaccineTypeEntity> vaccineTypeEntityList);
}
