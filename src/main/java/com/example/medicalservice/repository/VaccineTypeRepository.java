package com.example.medicalservice.repository;

import com.example.medicalservice.model.entity.VaccineTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccineTypeRepository extends JpaRepository<VaccineTypeEntity, String> {
}
