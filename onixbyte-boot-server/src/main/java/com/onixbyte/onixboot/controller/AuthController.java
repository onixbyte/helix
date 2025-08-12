package com.onixbyte.onixboot.controller;

import com.onixbyte.onixboot.dataset.view.UserView;
import com.onixbyte.onixboot.exception.BizException;
import com.onixbyte.onixboot.security.data.WecomAuthentication;
import com.onixbyte.onixboot.service.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @GetMapping("/wecom/login")
    public ResponseEntity<UserView> wecomLogin(
            @RequestParam String code
    ) {
        var _authenticatedToken = authenticationManager.authenticate(WecomAuthentication
                .unauthenticated(code));

        if (!(_authenticatedToken instanceof WecomAuthentication authenticationToken)) {
            throw new BizException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error, type mismatching.");
        }

        var user = authenticationToken.getDetails();

        var userToken = tokenService.createToken(authenticationToken.getDetails());

        return ResponseEntity
                .status(HttpStatus.OK)
                .header("Authorization", userToken)
                .body(UserView.of(user));
    }
}
