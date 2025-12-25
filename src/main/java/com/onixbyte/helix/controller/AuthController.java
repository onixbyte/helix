package com.onixbyte.helix.controller;

import com.onixbyte.helix.domain.web.request.UsernamePasswordLoginRequest;
import com.onixbyte.helix.domain.web.response.LoginSuccessResponse;
import com.onixbyte.helix.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Perform login with username and password.
     *
     * @param request login request
     * @return detailed user info and authentication token
     */
    @PostMapping("/login")
    public LoginSuccessResponse loginWithUsernameAndPassword(
            @Validated @RequestBody UsernamePasswordLoginRequest request
    ) {
        return authService.login(request);
    }

    @GetMapping("/register-enabled")
    public boolean getRegisterEnabled() {
        return authService.getRegisterEnabled();
    }
}
