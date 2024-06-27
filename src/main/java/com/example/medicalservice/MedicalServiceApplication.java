package com.example.medicalservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@OpenAPIDefinition(
        servers = {
                @Server(
                        url = "/med",
                        description = "Gateway Server Url"
                )
        },
        security = {@SecurityRequirement(name = "Authorization")}
)
public class MedicalServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedicalServiceApplication.class, args);
    }

}
