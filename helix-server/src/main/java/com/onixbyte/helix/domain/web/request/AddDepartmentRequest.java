package com.onixbyte.helix.domain.web.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddDepartmentRequest(
        @NotBlank(message = "部门名称不能为空") String name,
        Long parentId,
        @NotNull(message = "排序编号不能为空") @Min(value = 1, message = "排序编号不能小于或等于 0") Integer sort,
        @NotBlank(message = "部门状态不能为空") String status
) {
}
