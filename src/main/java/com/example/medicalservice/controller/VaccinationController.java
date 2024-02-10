package com.example.medicalservice.controller;

import com.example.medicalservice.service.facade.vaccine.VaccineFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public void processFile(@RequestPart(value = "report") MultipartFile report) {
        facade.processVaccinationReport(report);
    }


}
