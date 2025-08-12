package com.onixbyte.onixboot.security.providers;

import com.onixbyte.onixboot.exception.WecomUserNotFoundException;
import com.onixbyte.onixboot.repository.UserRepository;
import com.onixbyte.onixboot.security.data.WecomAuthentication;
import com.onixbyte.onixboot.service.WecomService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class WecomAuthenticationProvider implements AuthenticationProvider {

    private final WecomService wecomService;
    private final UserRepository userRepository;

    public WecomAuthenticationProvider(WecomService wecomService, UserRepository userRepository) {
        this.wecomService = wecomService;
        this.userRepository = userRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (!(authentication instanceof WecomAuthentication authenticationToken)) {
            throw new IllegalStateException("Cannot perform authentication with Wecom.");
        }

        var wecomOpenId = wecomService.getWecomOpenId(authenticationToken.getPrincipal());

        var user = userRepository.selectByWecomOpenId(wecomOpenId);
        if (Objects.isNull(user)) {
            wecomService.sendRegisterMessage(List.of(wecomOpenId));
            throw new WecomUserNotFoundException("Cannot find user. Please authorise to register via Wecom.");
        }

        authenticationToken.setAuthenticated(true);
        authenticationToken.setUser(user);

        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return WecomAuthentication.class.isAssignableFrom(authentication);
    }
}
