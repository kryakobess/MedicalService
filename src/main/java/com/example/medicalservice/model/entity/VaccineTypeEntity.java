package com.example.medicalservice.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "vaccine_type")
@Data
public class VaccineTypeEntity {
    @Id
    private String code;

    @Column(name = "description")
    private String description;

    @Column(name = "duration_in_days")
    private Integer durationInDays;
}
