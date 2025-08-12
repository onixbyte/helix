package com.onixbyte.onixboot.security.providers;

import com.onixbyte.onixboot.exception.WecomUserNotFoundException;
import com.onixbyte.onixboot.repository.UserRepository;
import com.onixbyte.onixboot.security.data.WecomAuthentication;
import com.onixbyte.onixboot.service.WecomService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Wecom authentication provider.
 *
 * @author zihluwang
 */
@Component
public class WecomAuthenticationProvider implements AuthenticationProvider {

    private final WecomService wecomService;
    private final UserRepository userRepository;

    public WecomAuthenticationProvider(
            WecomService wecomService,
            UserRepository userRepository
    ) {
        this.wecomService = wecomService;
        this.userRepository = userRepository;
    }

    /**
     * Perform Wecom authentication.
     *
     * @param authentication the authentication request object
     * @return authenticated user information
     * @throws AuthenticationException if authentication failed
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (!(authentication instanceof WecomAuthentication authenticationToken)) {
            throw new IllegalStateException("Cannot perform authentication with Wecom.");
        }

        // Get Wecom open ID.
        var wecomOpenId = wecomService.getWecomOpenId(authenticationToken.getPrincipal());

        // Get user by Wecom Open ID.
        var user = userRepository.selectByWecomOpenId(wecomOpenId);
        if (Objects.isNull(user)) {
            // Wecom cannot directly obtain user information and cannot perform
            // automatic registration. Therefore, it is necessary to send a private message with a
            // registration link to the user.
            wecomService.sendRegisterMessage(wecomOpenId);
            throw new WecomUserNotFoundException("Cannot find user. Please authorise to register via Wecom.");
        }

        authenticationToken.setAuthenticated(true);
        authenticationToken.setUser(user);

        return authenticationToken;
    }

    /**
     * Returns {@code true} if this {@code AuthenticationProvider} supports the indicated
     * {@code Authentication} object.
     *
     * @param authentication authentication information
     * @return <code>true</code> if the implementation can more closely evaluate the
     * code>Authentication</code> class presented
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return WecomAuthentication.class.isAssignableFrom(authentication);
    }
}
