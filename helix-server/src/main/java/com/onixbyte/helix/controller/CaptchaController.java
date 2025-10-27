package com.onixbyte.helix.controller;

import com.onixbyte.helix.domain.web.response.CaptchaResponse;
import com.onixbyte.helix.service.CaptchaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/captcha")
public class CaptchaController {

    private final CaptchaService captchaService;

    public CaptchaController(CaptchaService captchaService) {
        this.captchaService = captchaService;
    }

    @GetMapping
    public ResponseEntity<CaptchaResponse> getCaptcha() {
        if (captchaService.isCaptchaEnabled()) {
            var captchaTuple = captchaService.buildCaptcha();
            return ResponseEntity.ok(new CaptchaResponse(
                    captchaTuple.left(),
                    captchaTuple.right())
            );
        }
        return ResponseEntity.noContent().build();
    }
}
