package com.onixbyte.helix.domain.web.request;

import com.onixbyte.helix.shared.MessageName;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;

public record QueryUserRequest(
        Long departmentId,
        String username,
        String regionAbbreviation,
        String phoneNumber,
        @Pattern(
                regexp = "^(ACTIVE|INACTIVE|LOCKED)?$",
                message = "{" + MessageName.REQUEST_QUERY_USER_STATUS_INVALID + "}")
        String status,
        LocalDateTime createdAtStart,
        LocalDateTime createdAtEnd
) {
}
