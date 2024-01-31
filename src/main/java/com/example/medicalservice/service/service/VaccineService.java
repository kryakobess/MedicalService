package com.example.medicalservice.service.service;

import com.example.medicalservice.model.model.ReportData;

import java.util.List;

public interface VaccineService {
    void createFromReportData(List<ReportData> reportData);
}
