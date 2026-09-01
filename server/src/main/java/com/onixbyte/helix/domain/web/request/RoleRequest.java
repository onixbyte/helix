package com.onixbyte.helix.domain.web.request;

import com.onixbyte.helix.enumeration.Status;
import com.onixbyte.helix.shared.MessageName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record RoleRequest(
        @NotBlank(message = "{" + MessageName.REQUEST_ROLE_NAME_NOT_EMPTY + "}")
        String name,
        @NotBlank(message = "{" + MessageName.REQUEST_ROLE_CODE_NOT_EMPTY + "}")
        String code,
        @NotNull(message = "{" + MessageName.REQUEST_ROLE_SORT_NOT_NULL + "}")
        Integer sort,
        Boolean defaultValue,
        String description,
        Status status
) {
}
