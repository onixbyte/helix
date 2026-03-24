package com.onixbyte.helix.domain.web.request;

import com.onixbyte.helix.enumeration.UserStatus;
import com.onixbyte.helix.shared.MessageName;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record EditUserRequest(
        @NotNull(message = "{" + MessageName.REQUEST_EDIT_USER_ID_NOT_NULL + "}")
        @Positive(message = "{" + MessageName.REQUEST_EDIT_USER_ID_POSITIVE + "}")
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
