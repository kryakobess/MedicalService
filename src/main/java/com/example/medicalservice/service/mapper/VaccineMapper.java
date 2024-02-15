package com.example.medicalservice.service.mapper;

import com.example.medicalservice.model.dto.VaccineDto;
import com.example.medicalservice.model.entity.VaccineTypeEntity;
import com.example.medicalservice.model.entity.Vaccine;
import com.example.medicalservice.model.model.ReportData;
import com.example.medicalservice.service.mapper.PatientMapper;
import com.example.medicalservice.service.mapper.VaccinationPlaceMapper;
import com.example.medicalservice.service.mapper.VaccineTypeMapper;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
        VaccineTypeMapper.class,
        VaccinationPlaceMapper.class,
        PatientMapper.class
})
public interface VaccineMapper {

    @Mapping(target = "vaccinationPlace.organizationName", source = "reportData.vaccinationPlace")
    @Mapping(target = "vaccinationPlace.divisionName", source = "reportData.vaccinationDivision")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "vaccineTypeEntity", expression = "java(getTypeByCode(reportData, vaccinationTypeEntities))")
    @Mapping(target = "patient.firstName", source = "reportData.patientFirstName")
    @Mapping(target = "patient.secondName", source = "reportData.patientSecondName")
    @Mapping(target = "patient.documentNumber", source = "reportData.documentNumber")
    @Mapping(target = "vaccinationDate", source = "reportData.vaccinationDate")
    Vaccine fromReportData(ReportData reportData, @Context List<VaccineTypeEntity> vaccinationTypeEntities);

    @Mapping(target = "vaccineType", source = "vaccineTypeEntity")
    VaccineDto toDto(Vaccine entity);

    List<VaccineDto> toDtoList(List<Vaccine> vaccines);

    @Named("getTypeByCode")
    default VaccineTypeEntity getTypeByCode(ReportData reportData, List<VaccineTypeEntity> vaccinationTypeEntities) {
        return vaccinationTypeEntities.stream()
                .filter(it -> it.getCode().equals(reportData.getVaccine().name()))
                .findFirst().orElse(null);
    }
}
