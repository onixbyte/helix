package com.onixbyte.onixboot.controller;

import com.onixbyte.onixboot.dataset.view.UserView;
import com.onixbyte.onixboot.exception.BizException;
import com.onixbyte.onixboot.security.data.MsalAuthentication;
import com.onixbyte.onixboot.security.data.UsernamePasswordAuthentication;
import com.onixbyte.onixboot.service.TokenService;
import com.onixbyte.onixboot.web.request.MsalLoginRequest;
import com.onixbyte.onixboot.web.request.UsernamePasswordLoginRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Authentication and authorisation controller.
 *
 * @author zihluwang
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthController(
            AuthenticationManager authenticationManager,
            TokenService tokenService
    ) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserView> login(@RequestBody UsernamePasswordLoginRequest request) {
        var _authentication = authenticationManager.authenticate(UsernamePasswordAuthentication
                .unauthenticated(request.username(), request.password()));

        if (!(_authentication instanceof UsernamePasswordAuthentication authentication)) {
            throw new BizException(HttpStatus.UNAUTHORIZED, "Cannot perform login with username and password.");
        }

        var user = authentication.getDetails();
        var token = tokenService.createToken(authentication.getDetails());

        return ResponseEntity.status(HttpStatus.OK)
                .header("Authorization", "Bearer " + token)
                .body(UserView.of(user));
    }

    /**
     * Login with Microsoft account.
     *
     * @param request a request contains the token issued by Microsoft
     * @return login success response
     * @throws BizException if authentication process fails, see logs for detailed message
     */
    @PostMapping("/login/msal")
    public ResponseEntity<UserView> msalLogin(
            @RequestBody MsalLoginRequest request
    ) {
        var _authentication = authenticationManager.authenticate(MsalAuthentication
                .unauthenticated(request.msalToken()));

        if (!(_authentication instanceof MsalAuthentication authentication)) {
            throw new BizException(HttpStatus.UNAUTHORIZED, "Cannot perform login with Microsoft Entra ID.");
        }

        var user = authentication.getDetails();
        var token = tokenService.createToken(authentication.getDetails());

        return ResponseEntity
                .status(HttpStatus.OK)
                .header("Authorization", "Bearer " + token)
                .body(UserView.of(user));
    }
}
