package com.example.medicalservice.service.service.impl;

import com.example.medicalservice.model.entity.VaccineTypeEntity;
import com.example.medicalservice.repository.VaccineTypeRepository;
import com.example.medicalservice.service.service.VaccineTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VaccineTypeServiceImpl implements VaccineTypeService {

    private final VaccineTypeRepository vaccineTypeRepository;

    @Override
    public List<VaccineTypeEntity> getAll() {
        return vaccineTypeRepository.findAll();
    }
}
