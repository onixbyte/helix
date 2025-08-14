package com.onixbyte.onixboot.security.providers;

import com.onixbyte.onixboot.exception.BizException;
import com.onixbyte.onixboot.security.data.UsernamePasswordAuthentication;
import com.onixbyte.onixboot.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UsernamePasswordAuthenticationProvider(
            UserService userService,
            PasswordEncoder passwordEncoder
    ) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (!(authentication instanceof UsernamePasswordAuthentication _authentication)) {
            throw new IllegalStateException("Cannot perform login with username and password.");
        }

        var user = userService.getUserByUsername(_authentication.getPrincipal());
        if (Objects.isNull(user) || !passwordEncoder.matches(_authentication.getCredentials(), user.getPassword())) {
            throw new BizException(HttpStatus.UNAUTHORIZED, "Username or password is incorrect.");
        }

        // Erase user credential
        user.setPassword(null);
        _authentication.eraseCredentials();

        // Update authentication information
        _authentication.setAuthenticated(true);
        _authentication.setUser(user);
        return _authentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthentication.class.isAssignableFrom(authentication);
    }
}
