package com.onixbyte.helix.service;

import com.onixbyte.helix.domain.entity.Asset;
import com.onixbyte.helix.exception.BizException;
import com.onixbyte.helix.manager.AssetManager;
import com.onixbyte.helix.properties.AssetProperties;
import com.onixbyte.helix.utils.SecurityUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

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

    private final AssetProperties assetProperties;
    private final S3Client s3Client;
    private final AssetManager assetManager;

    public AssetService(
            AssetProperties assetProperties,
            S3Client s3Client,
            AssetManager assetManager
    ) {
        this.assetProperties = assetProperties;
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

        var currentUser = SecurityUtil.getCurrentUser();

        var fullKey = buildFullKey(prefix, file.getOriginalFilename());

        var request = PutObjectRequest.builder()
                .bucket(assetProperties.bucket())
                .key(fullKey)
                .contentType(file.getContentType())
                .contentLength(file.getSize())
                .build();

        s3Client.putObject(request, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));

        var asset = assetManager.save(Asset.builder()
                .key(fullKey)
                .uploadBy(currentUser.getId())
                .uploadTime(LocalDateTime.now())
                .build());

        var fileUrlBuilder = new StringBuilder(assetProperties.publicHost());
        if (assetProperties.pathStyle()) {
            fileUrlBuilder.append(assetProperties.bucket());
        }
        fileUrlBuilder.append("/").append(fullKey);
        return fileUrlBuilder.toString();
    }

    private String buildFullKey(String prefix, String fileName) {
        return String.format("%s/%s", prefix, fileName);
    }

    /**
     * Delete file with given asset ID.
     *
     * @param assetId ID of the asset
     */
    @Transactional(rollbackFor = Throwable.class)
    public void deleteAsset(Long assetId) {
        var currentUser = SecurityUtil.getCurrentUser();
        var asset = assetManager.queryByAssetId(assetId);

        if (!Objects.equals(currentUser.getId(), asset.getUploadBy())) {
            throw new BizException(HttpStatus.FORBIDDEN, "You are not able to delete an asset that is not uploaded by you.");
        }

        assetManager.deleteById(assetId);
        s3Client.deleteObject(DeleteObjectRequest.builder()
                .bucket(assetProperties.bucket())
                .key(asset.getKey())
                .build());
    }

    public void listFiles() {

    }
}
