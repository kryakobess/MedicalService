package com.example.medicalservice.controller;

import com.example.medicalservice.model.dto.VaccineDto;
import com.example.medicalservice.model.model.VaccinationFilter;
import com.example.medicalservice.service.facade.vaccine.VaccineFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/vaccination")
@RequiredArgsConstructor
public class VaccinationController {

    private final VaccineFacade facade;

    @Operation(description = "Consumes Multipart report in xls and xlsx format and stores provided data")
    @ApiResponses(value = {
            @ApiResponse(description = "Report processed", responseCode = "200"),
            @ApiResponse(description = "Validation error", responseCode = "400")
    })
    @PostMapping(value = "/processFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<VaccineDto>> processFile(@RequestPart(value = "report") MultipartFile report) {
        return ResponseEntity.ok(facade.processVaccinationReport(report));
    }

    @GetMapping("/get-all")
    @Operation(description = "Get all vaccination info in pageable format with filtering")
    public ResponseEntity<Page<VaccineDto>> getVaccinationInfoPage(
            VaccinationFilter filter,
            @ParameterObject Pageable pageable
    ) {
        return ResponseEntity.ok(facade.getVaccinationInfoPage(filter, pageable));
    }
}
