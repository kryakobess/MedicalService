package com.example.medicalservice.service.service.impl;

import com.example.medicalservice.model.entity.QVaccine;
import com.example.medicalservice.model.model.VaccinationFilter;
import com.example.medicalservice.service.service.PredicateFactory;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;
import static org.springframework.util.CollectionUtils.isEmpty;

@Service
@RequiredArgsConstructor
public class PredicateFactoryImpl implements PredicateFactory {
    @Override
    public Predicate getVaccinationFiltered(VaccinationFilter filter) {
        QVaccine vaccine = QVaccine.vaccine;
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (!isEmpty(filter.getIds())) {
            booleanBuilder.and(vaccine.id.in(filter.getIds()));
        }

        if (!isEmpty(filter.getVaccineTypes())) {
            booleanBuilder.and(vaccine.vaccineTypeEntity.code.in(filter.getVaccineTypes()));
        }

        if (!isEmpty(filter.getOrganizationNames())) {
            booleanBuilder.and(vaccine.vaccinationPlace.organizationName.in(filter.getOrganizationNames()));
        }

        if (!isEmpty(filter.getDivisionNames())) {
            booleanBuilder.and(vaccine.vaccinationPlace.divisionName.in(filter.getDivisionNames()));
        }

        if (!isEmpty(filter.getPatientFirstNames())) {
            booleanBuilder.and(vaccine.patient.firstName.in(filter.getPatientFirstNames()));
        }

        if (!isEmpty(filter.getPatientSecondNames())) {
            booleanBuilder.and(vaccine.patient.secondName.in(filter.getPatientSecondNames()));
        }

        if (nonNull(filter.getDateFrom())) {
            if (nonNull(filter.getDateUntil())) {
                booleanBuilder.and(vaccine.vaccinationDate.between(filter.getDateFrom(), filter.getDateUntil()));
            } else {
                booleanBuilder.and(vaccine.vaccinationDate.after(filter.getDateFrom()));
            }
        } else if (nonNull(filter.getDateUntil())) {
            booleanBuilder.and(vaccine.vaccinationDate.before(filter.getDateUntil()));
        }

        return booleanBuilder;
    }
}
