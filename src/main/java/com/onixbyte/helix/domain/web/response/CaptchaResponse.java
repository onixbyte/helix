package com.onixbyte.helix.domain.web.response;

public record CaptchaResponse(
        String captcha,
        String uuid
) {
}
