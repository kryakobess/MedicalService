package com.example.medicalservice.configuration.feign;

import com.example.medicalservice.service.service.AuthorizationService;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class FeignAuthInterceptor implements RequestInterceptor {

    private final AuthorizationService authorizationService;
    @Override
    public void apply(RequestTemplate template) {
        var jwt = authorizationService.getServiceToken();
        log.debug("Adding jwt to internal request {}", jwt.substring(0, 30));
        template.header(HttpHeaders.AUTHORIZATION, jwt);
    }
}
