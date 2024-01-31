package com.example.medicalservice.model.exception;

public class FileUploadingException extends RuntimeException {

    public FileUploadingException(String message) {
        super(message);
    }

    public FileUploadingException(String message, Throwable cause) {
        super(message, cause);
    }
}
