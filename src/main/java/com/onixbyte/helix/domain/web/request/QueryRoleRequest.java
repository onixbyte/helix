package com.onixbyte.helix.domain.web.request;

import jakarta.validation.constraints.Pattern;

public record QueryRoleRequest(
        String name,
        String code,
        @Pattern(
                regexp = "^(ACTIVE|INACTIVE)?$",
                message = "状态仅可以是 ACTIVE、INACTIVE 其中之一")
        String status
) {
}
