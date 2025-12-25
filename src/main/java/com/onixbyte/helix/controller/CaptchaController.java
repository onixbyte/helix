package com.onixbyte.helix.controller;

import com.onixbyte.helix.domain.web.response.CaptchaResponse;
import com.onixbyte.helix.service.CaptchaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/captcha")
public class CaptchaController {

    private final CaptchaService captchaService;

    public CaptchaController(CaptchaService captchaService) {
        this.captchaService = captchaService;
    }

    @GetMapping
    public ResponseEntity<CaptchaResponse> getCaptcha() {
        var captchaTuple = captchaService.buildCaptcha();
        return Optional.ofNullable(captchaTuple)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }
}
