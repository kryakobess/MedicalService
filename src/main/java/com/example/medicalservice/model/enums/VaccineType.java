package com.example.medicalservice.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum VaccineType {
    SPUTNIK_V("Спутник V"),
    SPUTNIK_LIGHT("Спутник Лайт"),
    EPI_VAC_CORONA("ЭпиВакКорона"),
    COVI_VAC("КовиВак"),
    PFIZER("Pfizer/BioNTech"),
    MODERNA("Moderna"),
    ASTRA_ZENECA("AstraZeneca"),
    SINOPHARM("Sinopharm"),
    SINOVAC("Sinovac");

    private final String description;
}
