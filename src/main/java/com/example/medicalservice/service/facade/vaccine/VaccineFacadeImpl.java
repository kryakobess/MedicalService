package com.example.medicalservice.service.facade.vaccine;

import com.example.medicalservice.model.dto.VaccineDto;
import com.example.medicalservice.model.exception.FileUploadingException;
import com.example.medicalservice.model.model.VaccinationFilter;
import com.example.medicalservice.service.mapper.VaccineMapper;
import com.example.medicalservice.service.service.VaccinationReportProcessor;
import com.example.medicalservice.service.service.VaccineService;
import com.example.medicalservice.service.utils.FileManagerUtils;
import com.example.medicalservice.service.validator.FileReportValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class VaccineFacadeImpl implements VaccineFacade{

    private final FileReportValidator fileReportValidator;
    private final VaccineService vaccineService;
    private final VaccinationReportProcessor vaccinationReportProcessor;
    private final VaccineMapper vaccineMapper;

    @Override
    public List<VaccineDto> processVaccinationReport(MultipartFile report) {
        log.info("Getting POST processVaccinationReport request");
        fileReportValidator.validate(report);
        try (var reportFile = FileManagerUtils.convertMultipartToFile(report)) {
            var processedReport = vaccinationReportProcessor.processReport(reportFile.getReport());
            return vaccineMapper.toDtoList(vaccineService.createFromReportData(processedReport));
        } catch (IOException e) {
            log.error("Error while downloading file: ", e);
            throw new FileUploadingException("Error while downloading file", e);
        }
    }

    @Override
    public Page<VaccineDto> getVaccinationInfoPage(VaccinationFilter filter, Pageable pageable) {
        log.info("Request GET getVaccinationInfoPage with filter:{}", filter);
        return vaccineService.getVaccinePage(filter, pageable).map(vaccineMapper::toDto);
    }
}
