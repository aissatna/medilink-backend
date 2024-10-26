package com.aissatna.medilinkbackend.service.minio;

import io.minio.errors.MinioException;

import java.io.InputStream;
import java.util.List;

public interface IMinioService {

    /**
     * Get URL for Minio file
     *
     * @param path path in minio to the file
     * @return InputStream of file
     * @throws MinioException Minio Exception
     */
    InputStream get(String path) throws MinioException;

    /**
     * Upload InputStream to Minio
     *
     * @param destinationPath path of file to be upload in Minio
     * @param file            InpuStream of file to upload
     * @param contentType     String contentType of File
     * @throws MinioException Minio Exception
     */
    void upload(String destinationPath, InputStream file, String contentType) throws MinioException;

    /**
     * List Folder of specific path in Minio
     *
     * @param folder String path of folder to display
     * @return List<String> list of folder/file
     * @throws MinioException Minio Exception
     */
    List<String> listFolder(String folder) throws MinioException;

    /**
     * delete Minio file by path
     *
     * @param path path in minio to the file
     * @throws MinioException Minio Exception
     */
    void delete(String path) throws MinioException;

    /**
     * Get minio URL from file
     *
     * @param path path in minio to the file
     * @return String of URL
     * @throws MinioException Minio exception
     */
    String getUrl(String path) throws MinioException;
}
