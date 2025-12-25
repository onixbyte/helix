package com.onixbyte.helix.domain.web.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ResetPasswordRequest(
        @NotNull(message = "用户 ID 不能为空") Long id,
        @NotBlank(message = "密码不能为空") String password
) {
}
