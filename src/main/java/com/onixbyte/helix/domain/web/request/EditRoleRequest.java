package com.onixbyte.helix.domain.web.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record EditRoleRequest(
        @NotNull(message = "角色 ID 不能为空")
        Long id,
        String name,
        String code,
        Integer sort,
        Boolean defaultValue,
        String description,
        @Pattern(
                regexp = "^(ACTIVE|INACTIVE)?$",
                message = "状态仅可以是 ACTIVE、INACTIVE 其中之一")
        String status
) {
}
