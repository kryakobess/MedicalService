package com.example.medicalservice.controller;

import com.example.medicalservice.model.dto.VaccinationPlaceDto;
import com.example.medicalservice.service.facade.place.VaccinationPlaceFacade;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/place")
public class VaccinationPlaceController {

    private final VaccinationPlaceFacade facade;

    @Operation(description = "Get all registered vaccination places")
    @GetMapping("/vaccination-places")
    @PreAuthorize("hasAuthority('MEDICAL_VIEWER')")
    public ResponseEntity<Page<VaccinationPlaceDto>> getVaccinationPlaces(
            @PageableDefault(size = 20) Pageable pageable
    ) {
        return ResponseEntity.ok(facade.getVaccinationPlaces(pageable));
    }
}
