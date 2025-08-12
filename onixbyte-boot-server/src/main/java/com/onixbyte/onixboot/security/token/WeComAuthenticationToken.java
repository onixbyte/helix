package com.onixbyte.onixboot.security.token;

import com.onixbyte.onixboot.exception.BizException;
import com.onixbyte.onixboot.exception.WeComException;
import com.onixbyte.onixboot.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class WeComAuthenticationToken implements Authentication {

    private String code;

    private User user;

    private boolean authenticated;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getCredentials() {
        return null;
    }

    @Override
    public User getDetails() {
        return user;
    }

    @Override
    public String getPrincipal() {
        return code;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean authenticated) throws IllegalArgumentException {
        this.authenticated = authenticated;
    }

    @Override
    public String getName() {
        return user.getName();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public WeComAuthenticationToken(User user) {
        this.code = null;
        this.user = user;
        this.authenticated = Objects.nonNull(user);
    }

    public WeComAuthenticationToken(String code) {
        this.code = code;
        this.user = null;
        this.authenticated = false;
    }

    public static WeComAuthenticationToken unauthenticated(String code) {
        return new WeComAuthenticationToken(code);
    }

    public static WeComAuthenticationToken authenticated(User user) {
        if (Objects.isNull(user)) {
            throw new BizException(HttpStatus.INTERNAL_SERVER_ERROR, "User cannot be null for an authenticated token.");
        }

        return new WeComAuthenticationToken(user);
    }
}
