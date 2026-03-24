package com.onixbyte.helix.domain.web.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ResetPasswordRequest(
        @NotBlank(message = "密码不能为空") String password
) {
}
