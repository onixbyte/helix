package com.onixbyte.helix.controller;

import com.onixbyte.helix.domain.web.request.LoginRequest;
import com.onixbyte.helix.domain.web.response.UserDetailResponse;
import com.onixbyte.helix.service.AuthService;
import com.onixbyte.helix.service.TokenService;
import com.onixbyte.helix.service.UserService;
import com.onixbyte.helix.shared.TokenConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    private final AuthService authService;
    private final TokenService tokenService;
    private final UserService userService;

    @Autowired
    public AuthController(
            AuthService authService,
            TokenService tokenService,
            UserService userService
    ) {
        this.authService = authService;
        this.tokenService = tokenService;
        this.userService = userService;
    }

    /**
     * Perform login with username and password.
     *
     * @param request login request
     * @return detailed user info and authentication token
     */
    @PostMapping("/login")
    public ResponseEntity<UserDetailResponse> loginWithUsernameAndPassword(
            @Validated @RequestBody LoginRequest request
    ) {
        var user = authService.login(request);
        var token = tokenService.generateToken(user);

        var cookie = authService.buildCookie(TokenConstant.TOKEN_NAME, token);

        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(userService.getDetail(user));
    }

    @GetMapping("/register-enabled")
    public boolean getRegisterEnabled() {
        return authService.getRegisterEnabled();
    }
}
