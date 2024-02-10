package com.example.medicalservice.service.service;

import com.example.medicalservice.model.model.ReportData;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface VaccinationReportProcessor {
    List<ReportData> processReport(File reportFile) throws IOException;
}
