package com.onixbyte.helix.service;

import com.onixbyte.helix.domain.entity.Setting;
import com.onixbyte.helix.domain.entity.User;
import com.onixbyte.helix.domain.web.request.LoginRequest;
import com.onixbyte.helix.exception.BizException;
import com.onixbyte.helix.manager.*;
import com.onixbyte.helix.security.authentication.UsernamePasswordAuthentication;
import com.onixbyte.helix.shared.SettingName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthService.class);

    private final CaptchaManager captchaManager;
    private final AuthenticationManager authenticationManager;
    private final SettingManager settingManager;
    private final ApplicationManager applicationManager;

    @Autowired
    public AuthService(
            CaptchaManager captchaManager,
            AuthenticationManager authenticationManager,
            SettingManager settingManager,
            ApplicationManager applicationManager
    ) {
        this.captchaManager = captchaManager;
        this.authenticationManager = authenticationManager;
        this.settingManager = settingManager;
        this.applicationManager = applicationManager;
    }

    /**
     * Perform user login.
     *
     * @param request user login request
     * @return user information and user identity token
     * @throws BizException if the user does not exist, or the username and password are incorrect
     */
    public User login(LoginRequest request) {
        var captchaEnabled = Optional.ofNullable(settingManager.getSettingByName(SettingName.CAPTCHA_ENABLED))
                .map(Setting::asBoolean)
                .orElse(false);
        if (captchaEnabled) {
            var uuid = request.uuid();
            var rawCaptcha = captchaManager.getCaptcha(uuid);

            if (Objects.isNull(rawCaptcha) || rawCaptcha.isBlank()) {
                throw new BizException(HttpStatus.BAD_REQUEST, "未找到验证码");
            }
            if (!rawCaptcha.equalsIgnoreCase(request.captcha())) {
                throw new BizException(HttpStatus.BAD_REQUEST, "验证码错误");
            }
        }

        var _authentication = authenticationManager.authenticate(UsernamePasswordAuthentication
                .unauthenticated(request.username(), request.password()));
        if (!(_authentication instanceof UsernamePasswordAuthentication authentication)) {
            log.error(
                    "Type mismatched, required type is UsernamePasswordAuthentication but got {}.",
                    _authentication.getClass()
            );
            throw new BizException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Cannot perform login due to server crashes.");
        }

        return authentication.getDetails();
    }

    /**
     * Get whether the registration function is turned on.
     *
     * @return {@code true} if registration is enabled, otherwise {@code false}
     */
    public boolean getRegisterEnabled() {
        return Optional.ofNullable(settingManager.getSettingByName(SettingName.REGISTER_ENABLED))
                .map(Setting::asBoolean)
                .orElse(false);
    }

    public ResponseCookie buildCookie(String cookieName, String token) {
        var cookieBuilder = ResponseCookie.from(cookieName, token)
                .httpOnly(true)
                .secure(applicationManager.isSslEnabled())
                .path("/");

        if (applicationManager.isSecureCookieEnabled()) {
            cookieBuilder.domain(applicationManager.getExternalHost());
        }

        return cookieBuilder.build();
    }
}
