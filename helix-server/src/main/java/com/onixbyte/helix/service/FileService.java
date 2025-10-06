package com.onixbyte.helix.service;

import com.onixbyte.helix.properties.FileProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;

/**
 * Service interface for file storage operations.
 * <p>
 * Provides a unified interface for file upload, download, and deletion operations that can be
 * implemented by different storage backends (local filesystem, S3-compatible services, etc.).
 *
 * @author zihluwang
 * @since 1.0.0
 */
@Service
public class FileService {

    private final FileProperties fileProperties;
    private final S3Client s3Client;

    public FileService(FileProperties fileProperties, S3Client s3Client) {
        this.fileProperties = fileProperties;
        this.s3Client = s3Client;
    }

    /**
     * Uploads a file to the configured storage backend. The file will be validated according to the
     * storage configuration and stored with a unique filename to prevent conflicts.
     *
     * @param prefix prefix to the file, should not started with a {@code /}
     * @param file   the multipart file to upload
     * @return the URL or path that can be used to access the uploaded file
     * @throws IllegalArgumentException if the file is invalid (empty, too large, wrong extension,
     *                                  etc.)
     * @throws RuntimeException         if the upload operation fails
     */
    public String uploadFile(String prefix, MultipartFile file) throws IOException {
        // todo prefix check

        var fullKey = buildFullKey(prefix, file.getOriginalFilename());

        var request = PutObjectRequest.builder()
                .bucket(fileProperties.getBucket())
                .key(fullKey)
                .contentType(file.getContentType())
                .contentLength(file.getSize())
                .build();

        s3Client.putObject(request, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));

        var fileUrlBuilder = new StringBuilder(fileProperties.getPublicHost());
        if (fileProperties.isPathStyle()) {
            fileUrlBuilder.append(fileProperties.getBucket());
        }
        fileUrlBuilder.append("/").append(fullKey);
        return fileUrlBuilder.toString();
    }

    private String buildFullKey(String prefix, String fileName) {
        return String.format("%s/%s", prefix, fileName);
    }
}
