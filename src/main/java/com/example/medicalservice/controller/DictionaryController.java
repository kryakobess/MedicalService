package com.example.medicalservice.controller;

import com.example.medicalservice.model.dto.VaccineTypeDto;
import com.example.medicalservice.service.facade.dict.DictionaryFacade;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dictionary")
public class DictionaryController {

    private final DictionaryFacade dictionaryFacade;

    @Operation(description = "Get all vaccine types")
    @GetMapping("/vaccine-types")
    public ResponseEntity<List<VaccineTypeDto>> getVaccineTypes() {
        return ResponseEntity.ok(dictionaryFacade.getVaccineTypes());
    }
}
