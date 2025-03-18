package com.aissatna.medilinkbackend.service.file;

import com.aissatna.medilinkbackend.service.minio.IMinioService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;


@Service
@AllArgsConstructor
@Slf4j
public class FileService implements IFileService {
    private static final int MAX_FILE_SIZE_BYTES = 10000000; // 10 MB in bytes

    private final IMinioService minioService;


    @Override
    public String getFullFilePath(String relativePath) {
        if (relativePath == null) return null;
        return minioService.getFileUrl(relativePath);
    }

    @Override
    public boolean isValidFile(MultipartFile file) {
        return !file.isEmpty() && file.getSize() < MAX_FILE_SIZE_BYTES;
    }

    @Override
    public String getFileExtension(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        if (originalFilename != null && originalFilename.contains(".")) {
            return originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();
        }
        throw new IllegalArgumentException("The file has no extension or is invalid.");
    }

    @Override
    public void uploadFile(MultipartFile file, String path, String fileName) {
        if (!isValidFile(file)) {
            throw new IllegalArgumentException("Invalid file size ! Max 10 MB");
        }
        try {
            InputStream fileStream = file.getInputStream();
            String contentType = file.getContentType();
            minioService.uploadFile(fileStream, path,fileName ,contentType);
            log.info("File {} is uploaded successfully", fileName);

        } catch (IOException e) {
            log.error("Failed to process image file: {}", e.getMessage());
            throw new IllegalStateException("Error processing the file");
        }
    }

    @Override
    public void deleteFile(String filePath) {
        minioService.deleteObject(filePath);
    }
}
