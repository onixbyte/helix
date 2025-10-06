package com.onixbyte.helix.controller;

import com.onixbyte.helix.constant.FilePrefixes;
import com.onixbyte.helix.domain.web.response.FileUploadResponse;
import com.onixbyte.helix.exception.BizException;
import com.onixbyte.helix.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * REST controller for file storage operations. Provides endpoints for uploading, downloading, and
 * deleting files using the configured storage service.
 * 
 * @author zihluwang
 * @since 1.0.0
 */
@RestController
@RequestMapping("/files")
public class FileController {

    private static final Logger log = LoggerFactory.getLogger(FileController.class);

    private final FileService fileService;

    /**
     * Constructs a new FileController with the specified file service.
     *
     * @param fileService the file service to use for file operations
     */
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    /**
     * Uploads a file to the configured storage service.
     *
     * @param file the multipart file to upload
     * @return ResponseEntity containing the file URL and metadata, or error message
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<FileUploadResponse> uploadFile(
            @RequestParam MultipartFile file
    ) {
        try {
            if (file.isEmpty()) {
                throw new BizException(HttpStatus.BAD_REQUEST, "File cannot be empty.");
            }

            var fileUrl = fileService.uploadFile(FilePrefixes.UPLOADS, file);
            
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
}
