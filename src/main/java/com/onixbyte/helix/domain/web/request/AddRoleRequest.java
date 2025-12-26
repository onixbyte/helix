package com.onixbyte.helix.domain.web.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record AddRoleRequest(
        @NotBlank(message = "角色名称不能为空")
        String name,
        @NotBlank(message = "角色编码不能为空")
        String code,
        @NotNull(message = "排序编号不能为空")
        Integer sort,
        Boolean defaultValue,
        String description,
        @Pattern(
                regexp = "^(ACTIVE|INACTIVE)?$",
                message = "状态仅可以是 ACTIVE、INACTIVE 其中之一")
        String status
) {
}
