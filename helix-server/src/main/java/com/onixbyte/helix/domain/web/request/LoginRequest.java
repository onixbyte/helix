package com.onixbyte.helix.domain.web.request;

/**
 * Login data.
 *
 * @param username username
 * @param password password
 * @param uuid     captcha uuid
 * @param captcha  captcha code
 */
public record LoginRequest(
        String username,
        String password,
        String uuid,
        String captcha
) {
}
