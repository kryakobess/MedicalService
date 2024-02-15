package com.example.medicalservice.service.service;

import com.example.medicalservice.model.model.VaccinationFilter;
import com.querydsl.core.types.Predicate;

public interface PredicateFactory {
    Predicate getVaccinationFiltered(VaccinationFilter filter);
}
