package com.example.medicalservice.model.model;

import com.example.medicalservice.model.enums.DocumentType;
import com.example.medicalservice.model.enums.VaccineType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class ReportData {
    private String vaccinationPlace;
    private String vaccinationDivision;
    private VaccineType vaccine;
    private LocalDate vaccinationDate;
    private String patientFirstName;
    private String patientSecondName;
    private DocumentType documentType;
    private String documentNumber;
}
