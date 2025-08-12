package com.onixbyte.onixboot.controller;

import com.onixbyte.onixboot.dataset.view.UserView;
import com.onixbyte.onixboot.exception.BizException;
import com.onixbyte.onixboot.model.User;
import com.onixbyte.onixboot.security.token.WeComAuthenticationToken;
import com.onixbyte.onixboot.service.JsonWebTokenService;
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
    private final JsonWebTokenService jsonWebTokenService;

    public AuthController(AuthenticationManager authenticationManager, JsonWebTokenService jsonWebTokenService) {
        this.authenticationManager = authenticationManager;
        this.jsonWebTokenService = jsonWebTokenService;
    }

    @GetMapping("/we-com/login")
    public ResponseEntity<UserView> weComLogin(
            @RequestParam String code
    ) {
        var _authenticatedToken = authenticationManager.authenticate(WeComAuthenticationToken
                .unauthenticated(code));

        if (!(_authenticatedToken instanceof WeComAuthenticationToken authenticationToken)) {
            throw new BizException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error, type mismatching.");
        }

        var user = authenticationToken.getDetails();

        var userToken = jsonWebTokenService.createToken(authenticationToken.getDetails());

        return ResponseEntity
                .status(HttpStatus.OK)
                .header("Authorization", userToken)
                .body(UserView.of(user));
    }

    // @GetMapping("/we-com/register")
    // public ResponseEntity<UserView> weComRegister(
    //         @RequestParam String code
    // ) {
    //
    // }
}
