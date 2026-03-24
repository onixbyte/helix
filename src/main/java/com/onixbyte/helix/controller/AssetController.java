package com.onixbyte.helix.controller;

import com.onixbyte.helix.shared.AssetPrefix;
import com.onixbyte.helix.domain.web.response.FileUploadResponse;
import com.onixbyte.helix.exception.BizException;
import com.onixbyte.helix.service.AssetService;
import com.onixbyte.helix.shared.Message;
import com.onixbyte.helix.utils.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * This controller provides entry points that manipulates assets.
 *
 * @author zihluwang
 * @author siujamo
 */
@RestController
@RequestMapping("/assets")
public class AssetController {

    private static final Logger log = LoggerFactory.getLogger(AssetController.class);

    private final AssetService assetService;
    private final MessageUtil messageUtil;

    @Autowired
    public AssetController(
            AssetService assetService,
            MessageUtil messageUtil
    ) {
        this.assetService = assetService;
        this.messageUtil = messageUtil;
    }

    /**
     * Uploads a file to the configured storage service.
     *
     * @param file the multipart file to upload
     * @return ResponseEntity containing the file URL and metadata, or error message
     */
    @PostMapping
    public ResponseEntity<FileUploadResponse> uploadFile(
            @RequestParam MultipartFile file
    ) {
        try {
            if (file.isEmpty()) {
                throw new BizException(HttpStatus.BAD_REQUEST, messageUtil.getMessage(Message.ASSET_NOT_EMPTY));
            }

            var fileUrl = assetService.uploadFile(AssetPrefix.UPLOADS, file);

            return ResponseEntity.ok()
                    .header("Location", fileUrl)
                    .body(new FileUploadResponse(
                            file.getOriginalFilename(),
                            file.getContentType(),
                            file.getSize(),
                            fileUrl
                    ));
        } catch (Exception e) {
            log.error("File upload failed: {}", e.getMessage(), e);
            throw new BizException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Failed upload file: " + e.getMessage());
        }
    }

    /**
     * Delete an asset by asset ID.
     *
     * @param assetId asset ID
     */
    @DeleteMapping("/{id:\\d+}")
    public void deleteFile(
            @PathVariable("id") Long assetId
    ) {
        assetService.deleteAsset(assetId);
    }
}
