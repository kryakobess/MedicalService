package com.example.medicalservice.service.facade.vaccine;

import com.example.medicalservice.model.exception.FileUploadingException;
import com.example.medicalservice.service.service.VaccinationReportProcessor;
import com.example.medicalservice.service.service.VaccineService;
import com.example.medicalservice.service.utils.FileManagerUtils;
import com.example.medicalservice.service.validator.FileReportValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class VaccineFacadeImpl implements VaccineFacade{

    private final FileReportValidator fileReportValidator;
    private final VaccineService vaccineService;
    private final VaccinationReportProcessor vaccinationReportProcessor;

    @Override
    public void processVaccinationReport(MultipartFile report) {
        log.info("Getting POST processVaccinationReport request");
        fileReportValidator.validate(report);
        try (var reportFile = FileManagerUtils.convertMultipartToFile(report)) {
            var processedReport = vaccinationReportProcessor.processReport(reportFile.getReport());
            vaccineService.createFromReportData(processedReport);
        } catch (IOException e) {
            log.error("Error while downloading file: ", e);
            throw new FileUploadingException("Error while downloading file", e);
        }
    }
}
