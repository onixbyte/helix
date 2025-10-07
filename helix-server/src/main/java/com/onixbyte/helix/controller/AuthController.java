package com.onixbyte.helix.controller;

import com.onixbyte.helix.domain.entity.User;
import com.onixbyte.helix.domain.web.request.UsernamePasswordLoginRequest;
import com.onixbyte.helix.exception.BizException;
import com.onixbyte.helix.security.authentication.UsernamePasswordAuthentication;
import com.onixbyte.helix.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/username-password")
    public ResponseEntity<User> loginWithUsernameAndPassword(
            @RequestBody UsernamePasswordLoginRequest request
    ) {
        var _authentication = authenticationManager.authenticate(UsernamePasswordAuthentication.unauthenticated(request.username(), request.password()));
        if (!(_authentication instanceof UsernamePasswordAuthentication authentication)) {
            log.error("Type mismatched, required type is UsernamePasswordAuthentication but got {}.", _authentication.getClass());
            throw new BizException(HttpStatus.INTERNAL_SERVER_ERROR, "Cannot perform login due to server crashes.");
        }

        var token = tokenService.generateToken(authentication.getDetails());

        return ResponseEntity.status(HttpStatus.OK)
                .header("Authorization", token)
                .body(authentication.getDetails());
    }
}
