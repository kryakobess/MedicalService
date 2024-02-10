package com.example.medicalservice.service.facade.dict;

import com.example.medicalservice.model.dto.VaccineTypeDto;
import com.example.medicalservice.service.mapper.VaccineTypeMapper;
import com.example.medicalservice.service.service.VaccineTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DictionaryFacadeImpl implements DictionaryFacade {

    private final VaccineTypeService vaccineTypeService;
    private final VaccineTypeMapper vaccineTypeMapper;

    @Override
    public List<VaccineTypeDto> getVaccineTypes() {
        log.info("Getting GET getVaccineTypes request");
        return vaccineTypeMapper.toListVaccineTypeDto(vaccineTypeService.getAll());
    }
}
