package com.example.medicalservice.service.service.impl;

import com.example.medicalservice.model.enums.DocumentType;
import com.example.medicalservice.model.enums.VaccineType;
import com.example.medicalservice.model.exception.FileProcessorException;
import com.example.medicalservice.model.model.ReportData;
import com.example.medicalservice.service.service.VaccinationReportProcessor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.dhatim.fastexcel.reader.Cell;
import org.dhatim.fastexcel.reader.ReadableWorkbook;
import org.dhatim.fastexcel.reader.Row;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class VaccinationReportProcessorImpl implements VaccinationReportProcessor {

    private static final int SKIP_ROW_COUNT = 5;
    private static final int ROW_WITH_NAMES_NUMBER = 3;
    private static final int PLACE = 1;
    private static final int DIVISION = 2;
    private static final int VACCINE_NAME = 6;
    private static final int VACCINATION_DATE = 7;
    private static final int PATIENT_NAME = 8;
    private static final int DOCUMENT_NUMBER = 9;
    private static final List<String> COLUMN_NAMES =
            List.of("МО вакцинации", "Подразделение", "Дата назначения", "Вакцинация", "Статус вакцинации", "Наименование вакцины", "Дата вакцинации", "ФИО", "Номер пасспорта");

    @Override
    public List<ReportData> processReport(File reportFile) throws IOException {
        try (ReadableWorkbook workbook = new ReadableWorkbook(reportFile)) {
            var sheet = workbook.getFirstSheet();
            try (Stream<Row> rowStream = sheet.openStream()) {
                List<Row> rows = rowStream.toList();
                validateTemplateFormat(rows);
                return rows.stream().skip(SKIP_ROW_COUNT)
                        .parallel()
                        .map(r -> getReportData(r.getCells(0, DOCUMENT_NUMBER + 1)))
                        .toList();
            }
        }
    }

    private ReportData getReportData(List<Cell> cells) {
        return ReportData.builder()
                .vaccinationPlace(getVaccinationPlace(cells))
                .vaccinationDivision(getDivisionName(cells))
                .vaccine(getVaccineType(cells))
                .vaccinationDate(getVaccinationDate(cells))
                .patientFirstName(getFirstName(cells))
                .patientSecondName(getSecondName(cells))
                .documentNumber(getDocumentNumber(cells))
                .build();
    }

    private void validateTemplateFormat(List<Row> rows) {
        if (rows.size() <= ROW_WITH_NAMES_NUMBER) {
            throw new FileProcessorException("Inappropriate template. There is no values in report");
        }

        Row namedRow = rows.get(ROW_WITH_NAMES_NUMBER);
        if (!namedRow.hasCell(DOCUMENT_NUMBER)) {
            throw new FileProcessorException("Inappropriate template. Amount of columns must be " + DOCUMENT_NUMBER);
        }

        List<String> reportColumnNames = namedRow.getCells(PLACE, DOCUMENT_NUMBER + 1).stream()
                .map(Cell::getRawValue)
                .toList();
        if (!COLUMN_NAMES.equals(reportColumnNames)) {
            throw new FileProcessorException("Inappropriate template. Column names must contain:" + COLUMN_NAMES);
        }
    }

    private String getVaccinationPlace(List<Cell> cells) {
        Cell cell = cells.get(PLACE);
        checkNotEmptyCell(cell);
        return cell.getRawValue();
    }

    private String getDivisionName(List<Cell> cells) {
        Cell cell = cells.get(DIVISION);
        checkNotEmptyCell(cell);
        return cell.getRawValue();
    }

    private String getDocumentNumber(List<Cell> cells) {
        Cell cell = cells.get(DOCUMENT_NUMBER);
        checkNotEmptyCell(cell);
        var rawValue = cell.asNumber().toPlainString();
        return rawValue;
    }

    private VaccineType getVaccineType(List<Cell> cells) {
        Cell cell = cells.get(VACCINE_NAME);
        String rawValue = cell.getRawValue();
        return Arrays.stream(VaccineType.values())
                .filter(type -> type.getDescription().equalsIgnoreCase(rawValue))
                .findFirst()
                .orElseThrow(() -> new FileProcessorException(String.format("Unknown vaccine type at cell: %s", cell.getAddress().toString())));
    }

    private LocalDate getVaccinationDate(List<Cell> cells) {
        Cell cell = cells.get(VACCINATION_DATE);
        checkNotEmptyCell(cell);
        var format = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String rawValue = cell.asDate().format(format);
        return LocalDate.parse(rawValue, format);
    }

    private String getSecondName(List<Cell> cells) {
        Cell cell = cells.get(PATIENT_NAME);
        checkNotEmptyCell(cell);
        var nameParts = cell.getRawValue().split("\\s");
        checkNameParts(nameParts, cell);
        return nameParts[0];
    }

    private String getFirstName(List<Cell> cells) {
        Cell cell = cells.get(PATIENT_NAME);
        checkNotEmptyCell(cell);
        var nameParts = cell.getRawValue().split("\\s");
        checkNameParts(nameParts, cell);
        return nameParts[1];
    }

    private void checkNameParts(String[] nameParts, Cell cell) {
        if (nameParts.length != 2) {
            throw new FileProcessorException(String.format("Name should contain only first and second part. Cell: %s", cell.getAddress()));
        }
    }

    private void checkNotEmptyCell(Cell cell) {
        String rawValue = cell.getRawValue();
        if (StringUtils.isBlank(rawValue)) {
            throw new FileProcessorException(String.format("Cell: %s must not be empty", cell.getAddress().toString()));
        }
    }
}
