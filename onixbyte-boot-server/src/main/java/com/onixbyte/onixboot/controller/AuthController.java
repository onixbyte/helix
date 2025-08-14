package com.onixbyte.onixboot.controller;

import com.onixbyte.onixboot.dataset.view.UserView;
import com.onixbyte.onixboot.exception.BizException;
import com.onixbyte.onixboot.security.data.MsalAuthentication;
import com.onixbyte.onixboot.service.TokenService;
import com.onixbyte.onixboot.web.request.MsalLoginRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

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

    /**
     * Login with Microsoft account.
     *
     * @param request a request contains the token issued by Microsoft
     * @return login success response
     * @throws BizException if authentication process fails, see logs for detailed message
     */
    @GetMapping("/login/msal")
    public ResponseEntity<UserView> msalLogin(
            @RequestBody MsalLoginRequest request
    ) {
        var _authentication = authenticationManager.authenticate(MsalAuthentication
                .unauthenticated(request.msalToken()));

        if (!(_authentication instanceof MsalAuthentication authentication)) {
            throw new BizException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error, type mismatching.");
        }

        var user = authentication.getDetails();

        var token = tokenService.createToken(authentication.getDetails());

        return ResponseEntity
                .status(HttpStatus.OK)
                .header("Authorization", "Bearer " + token)
                .body(UserView.of(user));
    }
}
