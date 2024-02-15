package com.example.medicalservice.repository;

import com.example.medicalservice.model.entity.Vaccine;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Long>, QuerydslPredicateExecutor<Vaccine> {

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = {"vaccineTypeEntity","vaccinationPlace","patient"})
    Page<Vaccine> findAll(Predicate predicate, Pageable pageable);
}
