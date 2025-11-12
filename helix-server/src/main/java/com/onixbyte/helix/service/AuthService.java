package com.onixbyte.helix.service;

import com.onixbyte.helix.client.TokenClient;
import com.onixbyte.helix.domain.web.request.UsernamePasswordLoginRequest;
import com.onixbyte.helix.domain.web.response.LoginSuccessResponse;
import com.onixbyte.helix.exception.BizException;
import com.onixbyte.helix.manager.AuthSettingManager;
import com.onixbyte.helix.manager.CaptchaManager;
import com.onixbyte.helix.manager.CaptchaSettingManager;
import com.onixbyte.helix.security.authentication.UsernamePasswordAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthService.class);

    private final CaptchaManager captchaManager;
    private final CaptchaSettingManager captchaSettingManager;
    private final AuthenticationManager authenticationManager;
    private final TokenClient tokenClient;
    private final AuthSettingManager authSettingManager;

    public AuthService(CaptchaManager captchaManager, CaptchaSettingManager captchaSettingManager, AuthenticationManager authenticationManager, TokenClient tokenClient, AuthSettingManager authSettingManager) {
        this.captchaManager = captchaManager;
        this.captchaSettingManager = captchaSettingManager;
        this.authenticationManager = authenticationManager;
        this.tokenClient = tokenClient;
        this.authSettingManager = authSettingManager;
    }

    public LoginSuccessResponse login(UsernamePasswordLoginRequest request) {
        if (captchaSettingManager.isCaptchaEnabled()) {
            var uuid = request.uuid();
            var rawCaptcha = captchaManager.getCaptcha(uuid);

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

        var token = tokenClient.generateToken(authentication.getDetails());

        return new LoginSuccessResponse(token, authentication.getDetails());
    }

    public boolean getRegisterEnabled() {
        return authSettingManager.getRegisterEnabled();
    }
}
