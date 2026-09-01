package com.onixbyte.helix.domain.web.request;

import com.onixbyte.helix.enumeration.UserStatus;
import com.onixbyte.helix.shared.MessageName;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record AddUserRequest(
        @NotBlank(message = "{" + MessageName.REQUEST_ADD_USER_USERNAME_NOT_EMPTY + "}")
        String username,
        @NotBlank(message = "{" + MessageName.REQUEST_ADD_USER_PASSWORD_NOT_EMPTY + "}")
        String password,
        @NotBlank(message = "{" + MessageName.REQUEST_ADD_USER_FULL_NAME_NOT_EMPTY + "}")
        String fullName,
        String email,
        String regionAbbreviation,
        String phoneNumber,
        String avatarUrl,
        UserStatus status,
        Long departmentId,
        Long positionId,
        List<Long> roleIds
) {
}
