package com.onixbyte.helix.controller;

import com.onixbyte.helix.domain.web.request.UsernamePasswordLoginRequest;
import com.onixbyte.helix.domain.web.response.LoginSuccessResponse;
import com.onixbyte.helix.exception.BizException;
import com.onixbyte.helix.security.authentication.UsernamePasswordAuthentication;
import com.onixbyte.helix.service.CaptchaService;
import com.onixbyte.helix.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final CaptchaService captchaService;

    public AuthController(AuthenticationManager authenticationManager, TokenService tokenService, CaptchaService captchaService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.captchaService = captchaService;
    }

    /**
     * Perform login with username and password.
     *
     * @param request login request
     * @return detailed user info and authentication token
     */
    @PostMapping("/login")
    public LoginSuccessResponse loginWithUsernameAndPassword(
            @RequestBody UsernamePasswordLoginRequest request
    ) {
        if (captchaService.isCaptchaEnabled()) {
            var uuid = request.uuid();
            var rawCaptcha = captchaService.getCaptcha(uuid);

            if (Objects.isNull(rawCaptcha) || rawCaptcha.isBlank()) {
                throw new BizException(HttpStatus.BAD_REQUEST, "未找到验证码");
            }
            if (!rawCaptcha.equalsIgnoreCase(request.captcha())) {
                throw new BizException(HttpStatus.BAD_REQUEST, "验证码错误");
            }
        }

        var _authentication = authenticationManager.authenticate(UsernamePasswordAuthentication.unauthenticated(request.username(), request.password()));
        if (!(_authentication instanceof UsernamePasswordAuthentication authentication)) {
            log.error("Type mismatched, required type is UsernamePasswordAuthentication but got {}.", _authentication.getClass());
            throw new BizException(HttpStatus.INTERNAL_SERVER_ERROR, "Cannot perform login due to server crashes.");
        }

        var token = tokenService.generateToken(authentication.getDetails());

        return new LoginSuccessResponse(token, authentication.getDetails());
    }
}
