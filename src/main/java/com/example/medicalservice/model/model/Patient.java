package com.example.medicalservice.model.model;

import com.example.medicalservice.model.enums.DocumentType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Patient {
    private String firstName;
    private String secondName;
    private DocumentType documentType;
    private String documentNumber;
}
