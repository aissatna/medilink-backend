package com.aissatna.medilinkbackend.service.minio;

import com.aissatna.medilinkbackend.configuration.minio.MinioProperties;
import io.minio.*;
import io.minio.errors.MinioException;
import io.minio.http.Method;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
@Slf4j
public class MinioService implements IMinioService {

    private final MinioClient minioClient;
    private final MinioProperties minioProperties;

    @Override
    public void uploadFile(InputStream fileInputStream, String path, String fileName, String contentType) {
        String fullPath = path + fileName;
        executeWithExceptionHandling(() -> {
            PutObjectArgs args = PutObjectArgs.builder()
                    .bucket(minioProperties.getBucket())
                    .object(fullPath)
                    .stream(fileInputStream, fileInputStream.available(), -1)
                    .contentType(contentType != null ? contentType : "application/octet-stream") // Default content type
                    .build();
            minioClient.putObject(args);
            return null;
        }, "Failed to upload object to Minio");
    }

    @Override
    public String getFileUrl(String path) {
        if (!isObjectExist(path)) {
            return null;
        }
        try {
            return executeWithExceptionHandling(() -> {
                GetPresignedObjectUrlArgs args = GetPresignedObjectUrlArgs.builder()
                        .method(Method.GET)
                        .bucket(minioProperties.getBucket())
                        .expiry(2, TimeUnit.HOURS)
                        .object(path)
                        .build();
                return minioClient.getPresignedObjectUrl(args);
            }, "Failed to generate presigned URL for object");
        } catch (Exception e) {
            log.error("Failed to generate presigned URL for object {}: {}", path, e.getMessage());
            return null;
        }
    }

    @Override
    public void deleteObject(String path) {
        executeWithExceptionHandling(() -> {
            RemoveObjectArgs args = RemoveObjectArgs.builder()
                    .bucket(minioProperties.getBucket())
                    .object(path)
                    .build();
            minioClient.removeObject(args);
            return null;
        }, "Failed to delete object from Minio");
    }

    /**
     * PRIVATE METHODS
     */

    private <T> T executeWithExceptionHandling(CheckedSupplier<T> supplier, String errorMessage) {
        try {
            return supplier.get();
        } catch (MinioException e) {
            log.error("{}: {}", errorMessage, e.getMessage());
        } catch (IOException | InvalidKeyException | NoSuchAlgorithmException e) {
            log.error("{}: {}", errorMessage, e.getMessage());
            throw new IllegalStateException("Internal server error. Please retry later.", e);
        } catch (Exception e) {
            log.error("{}: {}", errorMessage, e.getMessage());
            throw new IllegalStateException(errorMessage, e);
        }
        return null;
    }

    private boolean isObjectExist(String name) {
        try {
            minioClient.statObject(StatObjectArgs.builder()
                    .bucket(minioProperties.getBucket())
                    .object(name).build());
            return true;
        } catch (Exception e) {
            log.error("Failed to check if object {} exists: {}", name, e.getMessage());
            return false;
        }
    }

    @FunctionalInterface
    private interface CheckedSupplier<T> {
        T get() throws Exception;
    }
}
