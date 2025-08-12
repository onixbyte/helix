package com.onixbyte.onixboot.security.data;

import com.onixbyte.onixboot.exception.BizException;
import com.onixbyte.onixboot.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class WecomAuthentication implements Authentication {

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

    public WecomAuthentication(User user) {
        this.code = null;
        this.user = user;
        this.authenticated = Objects.nonNull(user);
    }

    public WecomAuthentication(String code) {
        this.code = code;
        this.user = null;
        this.authenticated = false;
    }

    public static WecomAuthentication unauthenticated(String code) {
        return new WecomAuthentication(code);
    }

    public static WecomAuthentication authenticated(User user) {
        if (Objects.isNull(user)) {
            throw new BizException(HttpStatus.INTERNAL_SERVER_ERROR, "User cannot be null for an authenticated token.");
        }

        return new WecomAuthentication(user);
    }
}
