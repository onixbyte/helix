package com.onixbyte.onixboot.security.providers;

import com.onixbyte.onixboot.exception.WeComUserNotFoundException;
import com.onixbyte.onixboot.repository.UserRepository;
import com.onixbyte.onixboot.security.token.WeComAuthenticationToken;
import com.onixbyte.onixboot.service.WeComService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class WeComAuthenticationProvider implements AuthenticationProvider {

    private final WeComService weComService;
    private final UserRepository userRepository;

    public WeComAuthenticationProvider(WeComService weComService, UserRepository userRepository) {
        this.weComService = weComService;
        this.userRepository = userRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (!(authentication instanceof WeComAuthenticationToken authenticationToken)) {
            throw new IllegalStateException("Cannot perform authentication with WeCom.");
        }

        var weComOpenId = weComService.getWeComOpenId(authenticationToken.getPrincipal());

        var user = userRepository.selectByWeComOpenId(weComOpenId);
        if (Objects.isNull(user)) {
            weComService.sendRegisterMessage(List.of(weComOpenId));
            throw new WeComUserNotFoundException("Cannot find user. Please authorise to register via WeCom.");
        }

        authenticationToken.setAuthenticated(true);
        authenticationToken.setUser(user);

        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return WeComAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
