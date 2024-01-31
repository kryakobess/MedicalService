package com.example.medicalservice.service;

import com.example.medicalservice.model.entity.VaccineTypeEntity;
import com.example.medicalservice.model.entity.Vaccine;
import com.example.medicalservice.model.model.ReportData;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VaccineMapper {

    @Mapping(target = "vaccinationPlace.organizationName", source = "reportData.vaccinationPlace")
    @Mapping(target = "vaccinationPlace.divisionName", source = "reportData.vaccinationDivision")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "vaccineTypeEntity", expression = "java(getTypeByCode(reportData, vaccinationTypeEntities))")
    @Mapping(target = "patient.firstName", source = "reportData.patientFirstName")
    @Mapping(target = "patient.secondName", source = "reportData.patientSecondName")
    @Mapping(target = "patient.documentType", source = "reportData.documentType")
    @Mapping(target = "patient.documentNumber", source = "reportData.documentNumber")
    @Mapping(target = "vaccinationDate", source = "reportData.vaccinationDate")
    Vaccine fromReportData(ReportData reportData, @Context List<VaccineTypeEntity> vaccinationTypeEntities);

    @Named("getTypeByCode")
    default VaccineTypeEntity getTypeByCode(ReportData reportData, List<VaccineTypeEntity> vaccinationTypeEntities) {
        return vaccinationTypeEntities.stream()
                .filter(it -> it.getCode().equals(reportData.getVaccine().name()))
                .findFirst().orElse(null);
    }
}
