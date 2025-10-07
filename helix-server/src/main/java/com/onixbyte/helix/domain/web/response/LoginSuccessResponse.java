package com.onixbyte.helix.domain.web.response;

import com.onixbyte.helix.domain.entity.User;

public record LoginSuccessResponse(
        String accessToken,
        User user
) {

    public LoginSuccessResponse {
        user.setPassword(null);
    }
}
