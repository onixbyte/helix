package com.onixbyte.helix.security.provider;

import com.onixbyte.helix.domain.entity.Authority;
import com.onixbyte.helix.exception.BizException;
import com.onixbyte.helix.manager.AuthorityManager;
import com.onixbyte.helix.manager.UserManager;
import com.onixbyte.helix.security.authentication.UsernamePasswordAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    private static final Logger log = LoggerFactory.getLogger(UsernamePasswordAuthenticationProvider.class);
    private final UserManager userManager;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityManager authorityManager;

    public UsernamePasswordAuthenticationProvider(UserManager userManager, PasswordEncoder passwordEncoder, AuthorityManager authorityManager) {
        this.userManager = userManager;
        this.passwordEncoder = passwordEncoder;
        this.authorityManager = authorityManager;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (!(authentication instanceof UsernamePasswordAuthentication usernamePasswordAuthentication)) {
            throw new BizException(HttpStatus.INTERNAL_SERVER_ERROR, "用户认证失败，请稍后再试。");
        }

        // get user from database
        var user = userManager.queryByUsername(usernamePasswordAuthentication.getPrincipal());
        if (Objects.isNull(user)) {
            log.error("User {} is trying to authenticate but no user found.", usernamePasswordAuthentication.getPrincipal());
            throw new BizException(HttpStatus.UNAUTHORIZED, "用户名或密码错误。");
        }

        // validate password
        if (!passwordEncoder.matches(usernamePasswordAuthentication.getCredentials(), user.getPassword())) {
            log.error("User {} is trying to authenticate but password is incorrect.", usernamePasswordAuthentication.getPrincipal());
            throw new BizException(HttpStatus.UNAUTHORIZED, "用户名或密码错误。");
        }

        // erase credentials
        user.setPassword(null);
        usernamePasswordAuthentication.eraseCredentials();

        // get authorities
        var authorities = authorityManager.queryByUserId(user.getId())
                .stream()
                .map(Authority::asGrantedAuthority)
                .toList();

        // set values
        usernamePasswordAuthentication.setAuthenticated(true);
        usernamePasswordAuthentication.setDetails(user);
        usernamePasswordAuthentication.setAuthorities(authorities);

        return usernamePasswordAuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthentication.class.isAssignableFrom(authentication);
    }
}
