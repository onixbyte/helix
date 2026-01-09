package com.onixbyte.helix.filter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.onixbyte.helix.client.TokenClient;
import com.onixbyte.helix.manager.AuthorityManager;
import com.onixbyte.helix.manager.UserManager;
import com.onixbyte.helix.security.authentication.UsernamePasswordAuthentication;
import com.onixbyte.helix.shared.TokenConstant;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final static Logger log = LoggerFactory.getLogger(TokenAuthenticationFilter.class);

    private final UserManager userManager;
    private final AuthorityManager authorityManager;
    private final TokenClient tokenClient;

    public TokenAuthenticationFilter(
            UserManager userManager,
            AuthorityManager authorityManager,
            TokenClient tokenClient
    ) {
        this.userManager = userManager;
        this.authorityManager = authorityManager;
        this.tokenClient = tokenClient;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        var token = Optional.ofNullable(WebUtils.getCookie(request, TokenConstant.TOKEN_NAME))
                .map(Cookie::getValue)
                .orElse(null);
        if (Objects.isNull(token) || token.isBlank()) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            var decodedToken = tokenClient.verifyToken(token);
            var username = decodedToken.getSubject();

            var user = userManager.selectByUsername(username);
            var authorities = authorityManager.queryByUserId(user.getId())
                    .stream()
                    .map((authority) -> (GrantedAuthority) authority::getCode)
                    .toList();

            user.setPassword(null);

            var authentication = UsernamePasswordAuthentication.authenticated(user, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        } catch (JWTVerificationException e) {
            log.error("JWT verification failed.", e);
            filterChain.doFilter(request, response);
        }
    }
}
