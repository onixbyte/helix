package com.onixbyte.helix.service;

import com.onixbyte.helix.domain.entity.Asset;
import com.onixbyte.helix.exception.BizException;
import com.onixbyte.helix.manager.AssetManager;
import com.onixbyte.helix.properties.FileProperties;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

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
public class AssetService {

    private final FileProperties fileProperties;
    private final S3Client s3Client;
    private final AssetManager assetManager;

    public AssetService(
            FileProperties fileProperties,
            S3Client s3Client,
            AssetManager assetManager
    ) {
        this.fileProperties = fileProperties;
        this.s3Client = s3Client;
        this.assetManager = assetManager;
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
    @Transactional(rollbackFor = {Throwable.class})
    public String uploadFile(String prefix, MultipartFile file) throws IOException {
        if (Objects.isNull(prefix) || prefix.isBlank() || prefix.startsWith("/") || prefix.startsWith("..")) {
            throw new BizException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Prefix must not be empty, and should not start with '/' or '..'."
            );
        }

        var fullKey = buildFullKey(prefix, file.getOriginalFilename());

        var request = PutObjectRequest.builder()
                .bucket(fileProperties.bucket())
                .key(fullKey)
                .contentType(file.getContentType())
                .contentLength(file.getSize())
                .build();

        s3Client.putObject(request, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));

        var asset = assetManager.save(Asset.builder()
                .key(fullKey)
                .uploadBy(1L)
                .uploadTime(LocalDateTime.now())
                .build());

        var fileUrlBuilder = new StringBuilder(fileProperties.publicHost());
        if (fileProperties.pathStyle()) {
            fileUrlBuilder.append(fileProperties.bucket());
        }
        fileUrlBuilder.append("/").append(fullKey);
        return fileUrlBuilder.toString();
    }

    private String buildFullKey(String prefix, String fileName) {
        return String.format("%s/%s", prefix, fileName);
    }

    /**
     * Delete file with given file ID.
     *
     * @param fileId ID of the file
     */
    public void deleteFile(Long fileId) {
        // s3Client.deleteObject(DeleteObjectRequest.builder()
        //         .bucket(fileProperties.bucket())
        //         .key(fileKey)
        //         .build());
    }

    public void listFiles() {

    }
}
