package com.example.medicalservice.service.facade.dict;

import com.example.medicalservice.model.dto.VaccineTypeDto;

import java.util.List;

public interface DictionaryFacade {
    List<VaccineTypeDto> getVaccineTypes();
}
