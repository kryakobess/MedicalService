package com.example.medicalservice.service.service.impl;

import com.example.medicalservice.model.dto.AuthJwtRequestDto;
import com.example.medicalservice.service.client.AuthClient;
import com.example.medicalservice.service.service.AuthorizationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorizationServiceImpl implements AuthorizationService {

    @Value("${jwt.api-name}")
    private String name;

    @Value("${jwt.api-key}")
    private String key;

    private final AuthClient authClient;

    @Override
    @Cacheable(value = "serviceAuthToken", cacheManager = "jwtServiceRequestCacheManager")
    public String getServiceToken() {
        var tokenDto = authClient.authorize(
                AuthJwtRequestDto.builder()
                        .username(name)
                        .password(key)
                        .build()
        );
        log.info("Got token: {}", tokenDto.getToken().substring(0, 20));
        return tokenDto.getToken();
    }

}
