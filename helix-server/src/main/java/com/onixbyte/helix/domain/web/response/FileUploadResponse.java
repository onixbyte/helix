package com.onixbyte.helix.domain.web.response;

public record FileUploadResponse(
        String originalFileName,
        String contentType,
        Long size,
        String url
) {
}
