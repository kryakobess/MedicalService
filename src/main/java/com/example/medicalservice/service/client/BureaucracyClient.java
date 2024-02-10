package com.example.medicalservice.service.client;

import com.example.medicalservice.model.dto.CitizenInfoRequestDto;
import com.example.medicalservice.model.dto.CitizenInfoResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "bureaucracyService", path = "/person")
public interface BureaucracyClient {

    @GetMapping("/getByNameAndDocument")
    CitizenInfoResponseDto getByNameAndDocument(@SpringQueryMap CitizenInfoRequestDto citizenInfoRequestDto);
}
