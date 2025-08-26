package com.onixbyte.helix.domain.web.request;

import java.io.Serializable;

/**
 * Login request with username and password.
 *
 * @param username username
 * @param password raw password
 * @author zihluwang
 */
public record UsernamePasswordLoginRequest(
        String username,
        String password
) implements Serializable {
}
