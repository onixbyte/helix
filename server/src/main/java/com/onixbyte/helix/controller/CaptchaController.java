package com.onixbyte.helix.controller;

import com.onixbyte.helix.domain.web.response.CaptchaResponse;
import com.onixbyte.helix.service.CaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * This controller provides entry points to get captcha images.
 *
 * @author zihluwang
 * @author siujamo
 */
@RestController
@RequestMapping("/captcha")
public class CaptchaController {

    private final CaptchaService captchaService;

    @Autowired
    public CaptchaController(CaptchaService captchaService) {
        this.captchaService = captchaService;
    }

    /**
     * Get captcha image and captcha uuid.
     *
     * @return captcha response, contains the uuid of the captcha and image BASE64
     */
    @GetMapping
    public ResponseEntity<CaptchaResponse> getCaptcha() {
        var captchaTuple = captchaService.buildCaptcha();
        return Optional.ofNullable(captchaTuple)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }
}
