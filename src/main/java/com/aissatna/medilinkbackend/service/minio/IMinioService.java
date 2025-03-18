package com.aissatna.medilinkbackend.service.minio;

import java.io.InputStream;

public interface IMinioService {

    void uploadFile(InputStream fileInputStream, String path, String fileName, String contentType);
    String getFileUrl(String path) ;
    void deleteObject(String path);
}
