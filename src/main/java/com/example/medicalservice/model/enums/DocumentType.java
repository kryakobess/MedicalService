package com.example.medicalservice.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum DocumentType {
    IDENTITY_PASSPORT("Пасспорт"),
    INTERNATIONAL_PASSPORT("Загран Пасспорт"),
    DRIVER_LICENCE("Водительские права");

    private final String description;
}
