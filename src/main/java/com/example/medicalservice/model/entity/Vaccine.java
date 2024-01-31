package com.example.medicalservice.model.entity;

import com.example.medicalservice.model.model.Patient;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "vaccine")
@Data
public class Vaccine {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vaccine_seq")
    @SequenceGenerator(allocationSize = 1, name = "vaccine_seq", sequenceName = "vaccine_seq")
    private Long id;

    @Column(name = "vaccination_date")
    private LocalDate vaccinationDate;

    @ManyToOne
    @JoinColumn(name = "vaccine_type_code")
    private VaccineTypeEntity vaccineTypeEntity;

    @ManyToOne
    @JoinColumn(name = "vaccination_place_id")
    private VaccinationPlace vaccinationPlace;

    @Column(name = "citizen_id")
    private Long citizenId;

    @Transient
    private Patient patient;
}
