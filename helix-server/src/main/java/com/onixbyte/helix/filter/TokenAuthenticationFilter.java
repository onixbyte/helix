package com.onixbyte.helix.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.onixbyte.helix.manager.AuthorityManager;
import com.onixbyte.helix.manager.UserManager;
import com.onixbyte.helix.security.authentication.UsernamePasswordAuthentication;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final static Logger log = LoggerFactory.getLogger(TokenAuthenticationFilter.class);

    private final Algorithm algorithm;
    private final UserManager userManager;
    private final AuthorityManager authorityManager;

    public TokenAuthenticationFilter(Algorithm algorithm, UserManager userManager, AuthorityManager authorityManager) {
        this.algorithm = algorithm;
        this.userManager = userManager;
        this.authorityManager = authorityManager;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        var token = request.getHeader("Authorization");
        if (Objects.isNull(token) || token.isBlank()) {
            filterChain.doFilter(request, response);
            return;
        }

        if (!token.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        token = token.substring(7);
        var verifier = JWT.require(algorithm)
                .withIssuer("Helix Server")
                .build();

        try {
            var decodedToken = verifier.verify(token);
            var username = decodedToken.getSubject();

            var user = userManager.queryByUsername(username);
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
            throw new AuthenticationServiceException("Authentication failed due to incorrect or expired token.", e);
        }
    }
}
