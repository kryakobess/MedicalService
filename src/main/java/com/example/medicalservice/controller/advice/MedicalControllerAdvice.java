package com.example.medicalservice.controller.advice;

import com.example.medicalservice.model.dto.ApiError;
import com.example.medicalservice.model.exception.FileProcessorException;
import com.example.medicalservice.model.exception.FileUploadingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MedicalControllerAdvice {

    @ExceptionHandler(value = FileProcessorException.class)
    public ResponseEntity<ApiError> processFileReportException(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiError.builder()
                        .message("Error processing file")
                        .reason(e.getMessage())
                        .build()
                );
    }

    @ExceptionHandler(value = FileUploadingException.class)
    public ResponseEntity<ApiError> uploadFileException(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiError.builder()
                        .message("Error uploading file")
                        .reason(e.getMessage())
                        .build()
                );
    }
}
