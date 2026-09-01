package com.onixbyte.helix.domain.web.request;

import com.onixbyte.helix.shared.MessageName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ResetPasswordRequest(
        @NotBlank(message = "{" + MessageName.REQUEST_RESET_PASSWORD_NOT_EMPTY + "}") String password
) {
}
