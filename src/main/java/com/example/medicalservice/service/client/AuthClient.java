package com.example.medicalservice.service.client;

import com.example.medicalservice.configuration.feign.AuthClientConfig;
import com.example.medicalservice.model.dto.AuthJwtRequestDto;
import com.example.medicalservice.model.dto.AuthTokenResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "AuthService", path = "/", configuration = {AuthClientConfig.class})
public interface AuthClient {
    @PostMapping("/login")
    AuthTokenResponseDto authorize(@RequestBody AuthJwtRequestDto jwtRequestDto);
}
