package com.example.medicalservice.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "VACCINATION_PLACE")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class VaccinationPlace {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VACCINATION_PLACE_SEQ")
    @SequenceGenerator(allocationSize = 1, name = "VACCINATION_PLACE_SEQ", sequenceName = "VACCINATION_PLACE_SEQ")
    private Integer id;

    @Column(name = "ORGANIZATION_NAME")
    private String organizationName;

    @Column(name = "DIVISION_NAME")
    private String divisionName;
}
