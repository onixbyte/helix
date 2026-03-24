package com.onixbyte.helix.domain.web.request;

import com.onixbyte.helix.enumeration.Status;
import com.onixbyte.helix.validation.group.OnCreate;
import com.onixbyte.helix.validation.group.OnUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

public record AuthorityRequest(
        @Null(groups = {OnUpdate.class}, message = "Code cannot be edited.")
        @NotNull(groups = {OnCreate.class}, message = "Code cannot be null.")
        String code,
        @NotNull(message = "Name of the authority cannot be null")
        @NotBlank(message = "Name of the authority cannot be null")
        String name,
        String description,
        Status status
) {
}
