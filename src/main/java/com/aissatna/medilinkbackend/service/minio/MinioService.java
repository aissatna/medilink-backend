package com.aissatna.medilinkbackend.service.minio;

import com.aissatna.medilinkbackend.configuration.minio.MinioProperties;
import io.minio.*;
import io.minio.errors.MinioException;
import io.minio.http.Method;
import io.minio.messages.Item;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
@Slf4j
public class MinioService implements IMinioService {

    private final MinioClient minioClient;
    private final MinioProperties minioProperties;

    @Override
    public InputStream get(String path) {
        return executeWithExceptionHandling(() -> {
            GetObjectArgs args = GetObjectArgs.builder()
                    .bucket(minioProperties.getBucket())
                    .object(path)
                    .build();
            return minioClient.getObject(args);
        }, "Failed to get object from Minio");
    }

    @Override
    public void upload(String destinationPath, InputStream file, String contentType) {
        executeWithExceptionHandling(() -> {
            PutObjectArgs args = PutObjectArgs.builder()
                    .bucket(minioProperties.getBucket())
                    .object(destinationPath)
                    .stream(file, file.available(), -1)
                    .contentType(contentType != null ? contentType : "application/octet-stream") // Default content type
                    .build();
            minioClient.putObject(args);
            return null;
        }, "Failed to upload object to Minio");
    }

    public String getUrl(String path) {
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
    public List<String> listFolder(String folder) {
        return executeWithExceptionHandling(() -> {
            List<String> results = new ArrayList<>();
            ListObjectsArgs args = ListObjectsArgs.builder()
                    .bucket(minioProperties.getBucket())
                    .prefix(folder)
                    .recursive(true)
                    .build();
            Iterable<Result<Item>> resp = minioClient.listObjects(args);

            for (Result<Item> itemResult : resp) {
                results.add(itemResult.get().objectName());
            }
            return results;
        }, "Failed to list objects in folder");
    }

    @Override
    public void delete(String path) {
        executeWithExceptionHandling(() -> {
            RemoveObjectArgs args = RemoveObjectArgs.builder()
                    .bucket(minioProperties.getBucket())
                    .object(path)
                    .build();
            minioClient.removeObject(args);
            return null;
        }, "Failed to delete object from Minio");
    }

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
            throw new RuntimeException(errorMessage, e);
        }
        return null;
    }

    @FunctionalInterface
    private interface CheckedSupplier<T> {
        T get() throws Exception;
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
}
