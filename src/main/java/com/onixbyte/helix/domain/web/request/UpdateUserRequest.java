package com.onixbyte.helix.domain.web.request;

import com.onixbyte.helix.constant.UserStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record UpdateUserRequest(
        @NotNull(message = "User ID cannot be null")
        @Positive(message = "User ID must be positive")
        Long id,
        String fullName,
        String email,
        String regionAbbreviation,
        String phoneNumber,
        String avatarUrl,
        UserStatus status,
        Long departmentId,
        Long positionId
) {
}
