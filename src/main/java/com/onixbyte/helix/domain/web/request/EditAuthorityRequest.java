package com.onixbyte.helix.domain.web.request;

import jakarta.validation.constraints.Pattern;

public record EditAuthorityRequest(
        Long id,
        String name,
        String description,
        @Pattern(
                regexp = "^(ACTIVE|INACTIVE)?$",
                message = "状态参数错误")
        String status
) {
}
