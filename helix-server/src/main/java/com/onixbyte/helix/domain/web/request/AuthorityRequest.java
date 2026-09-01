package com.onixbyte.helix.domain.web.request;

import com.onixbyte.helix.enumeration.Status;
import com.onixbyte.helix.shared.MessageName;
import com.onixbyte.helix.validation.group.OnCreate;
import com.onixbyte.helix.validation.group.OnUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

public record AuthorityRequest(
        @Null(groups = {OnUpdate.class}, message = "{" + MessageName.REQUEST_AUTHORITY_CODE_NOT_EDITABLE + "}")
        @NotNull(groups = {OnCreate.class}, message = "{" + MessageName.REQUEST_AUTHORITY_CODE_NOT_NULL + "}")
        String code,
        @NotNull(message = "{" + MessageName.REQUEST_AUTHORITY_NAME_NOT_NULL + "}")
        @NotBlank(message = "{" + MessageName.REQUEST_AUTHORITY_NAME_NOT_NULL + "}")
        String name,
        String description,
        Status status
) {
}
