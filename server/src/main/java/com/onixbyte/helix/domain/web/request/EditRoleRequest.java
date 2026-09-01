package com.onixbyte.helix.domain.web.request;

import com.onixbyte.helix.shared.MessageName;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record EditRoleRequest(
        @NotNull(message = "{" + MessageName.REQUEST_EDIT_ROLE_ID_NOT_NULL + "}")
        Long id,
        String name,
        String code,
        Integer sort,
        Boolean defaultValue,
        String description,
        @Pattern(
                regexp = "^(ACTIVE|INACTIVE)?$",
                message = "{" + MessageName.REQUEST_EDIT_ROLE_STATUS_INVALID + "}")
        String status
) {
}
