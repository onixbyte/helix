package com.onixbyte.helix.domain.web.request;

import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;

public record QueryUserRequest(
        Long departmentId,
        String username,
        String regionAbbreviation,
        String phoneNumber,
        @Pattern(
                regexp = "^(ACTIVE|INACTIVE|LOCKED)?$",
                message = "状态仅可以是 ACTIVE、INACTIVE 或 LOCKED 其中之一")
        String status,
        LocalDateTime createdAtStart,
        LocalDateTime createdAtEnd
) {
}
