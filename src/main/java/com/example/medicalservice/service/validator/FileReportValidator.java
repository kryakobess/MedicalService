package com.example.medicalservice.service.validator;

import org.springframework.web.multipart.MultipartFile;

public interface FileReportValidator {

    void validate(MultipartFile file);
}
