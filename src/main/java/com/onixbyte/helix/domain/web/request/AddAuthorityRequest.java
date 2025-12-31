package com.onixbyte.helix.domain.web.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record AddAuthorityRequest(
        @NotNull @NotBlank(message = "权限编码不能为空") String code,
        @NotNull @NotBlank(message = "权限名称不能为空") String name,
        String description,
        @Pattern(
                regexp = "^(ACTIVE|INACTIVE)?$",
                message = "状态错误") String status
) {
}
