package com.example.medicalservice.service.utils;

import com.example.medicalservice.model.model.ReportFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileManagerUtils {
    private static final String savingPath = "./src/main/resources/";

    public static ReportFile convertMultipartToFile(MultipartFile file) throws IOException {
        File fileToSave = new File(savingPath + file.getOriginalFilename());

        Files.createDirectories(Paths.get(savingPath));
        fileToSave.createNewFile();

        try (OutputStream os = new FileOutputStream(fileToSave)) {
            os.write(file.getBytes());
        }

        return new ReportFile(fileToSave);
    }
}
