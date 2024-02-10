package com.example.medicalservice.service.validator;

import com.example.medicalservice.model.exception.FileUploadingException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class FileReportValidatorImpl implements FileReportValidator {

    private static final Set<String> ALLOWED_EXTENSIONS = Set.of("xls", "xlsx");

    @Override
    public void validate(MultipartFile file) {
        checkNotEmpty(file);
        checkExtension(file);
    }

    private void checkExtension(MultipartFile file) {
        String fileExtension = StringUtils.toRootLowerCase(FilenameUtils.getExtension(file.getOriginalFilename()));
        if (!ALLOWED_EXTENSIONS.contains(fileExtension)) {
            throw new FileUploadingException("Illegal file extension: " + fileExtension);
        }
    }

    private void checkNotEmpty(MultipartFile file) {
        if (file.isEmpty()) {
            throw new FileUploadingException("Cannot upload empty file");
        }
    }
}
