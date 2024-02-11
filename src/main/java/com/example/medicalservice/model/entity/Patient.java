package com.example.medicalservice.model.entity;

import com.example.medicalservice.configuration.AttributeEncryptor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "PATIENT")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PATIENT_SEQ")
    @SequenceGenerator(allocationSize = 1, name = "PATIENT_SEQ", sequenceName = "PATIENT_SEQ")
    private Long id;
    @Column(name = "CITIZEN_ID")
    private Long citizenId;
    @Column(name = "FIRST_NAME")
    @Convert(converter = AttributeEncryptor.class)
    private String firstName;
    @Column(name = "SECOND_NAME")
    @Convert(converter = AttributeEncryptor.class)
    private String secondName;
    @Column(name = "PASSPORT_NUMBER")
    @Convert(converter = AttributeEncryptor.class)
    private String documentNumber;
}
