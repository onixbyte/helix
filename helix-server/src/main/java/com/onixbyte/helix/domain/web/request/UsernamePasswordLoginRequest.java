package com.onixbyte.helix.domain.web.request;

public record UsernamePasswordLoginRequest(
        String username,
        String password
) {
}
