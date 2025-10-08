package com.onixbyte.helix.domain.web.request;

import com.onixbyte.helix.constant.UserStatus;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record AddUserRequest(
        @NotBlank(message = "Username cannot be empty.")
        String username,
        @NotBlank(message = "Password cannot be empty.")
        String password,
        @NotBlank(message = "Full name cannot be empty.")
        String fullName,
        String email,
        String countryCode,
        String phoneNumber,
        String avatarUrl,
        UserStatus status,
        Long departmentId,
        Long positionId,
        List<Long> roleIds
) {
}
