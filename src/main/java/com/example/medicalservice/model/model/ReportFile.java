package com.example.medicalservice.model.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

@AllArgsConstructor
@Getter
@Slf4j
public class ReportFile implements AutoCloseable{

    private File report;
    @Override
    public void close() {
        report.delete();
    }
}
