package com.aissatna.medilinkbackend.service.file;

import org.springframework.web.multipart.MultipartFile;

public interface IFileService {

    String getFullFilePath(String relativePath);
    boolean isValidFile(MultipartFile file);
    String getFileExtension(MultipartFile file);
    void uploadFile(MultipartFile file, String path,String fileName );
    void deleteFile(String fileName);
}
