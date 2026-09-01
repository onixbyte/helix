package com.onixbyte.helix.domain.web.request;

import com.onixbyte.helix.shared.MessageName;
import jakarta.validation.constraints.Pattern;

public record QueryRoleRequest(
        String name,
        String code,
        @Pattern(
                regexp = "^(ACTIVE|INACTIVE)?$",
                message = "{" + MessageName.REQUEST_QUERY_ROLE_STATUS_INVALID + "}")
        String status
) {
}
