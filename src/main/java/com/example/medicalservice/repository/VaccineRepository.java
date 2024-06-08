package com.example.medicalservice.repository;

import com.example.medicalservice.model.entity.Patient;
import com.example.medicalservice.model.entity.Vaccine;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Long>, QuerydslPredicateExecutor<Vaccine> {

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = {"vaccineTypeEntity","vaccinationPlace","patient"})
    Page<Vaccine> findAll(Predicate predicate, Pageable pageable);

    @Query("select v from Vaccine as v where v.patient = :patient order by v.vaccinationDate limit 1")
    Vaccine getLatestVaccinationDateForPatient(Patient patient);
}
