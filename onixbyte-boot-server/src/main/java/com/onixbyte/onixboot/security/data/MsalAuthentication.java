package com.onixbyte.onixboot.security.data;

import com.onixbyte.onixboot.exception.BizException;
import com.onixbyte.onixboot.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class MsalAuthentication implements Authentication {

    private String msalToken;

    private User user;

    private boolean authenticated;

    public MsalAuthentication(String msalToken, User user, boolean authenticated) {
        this.msalToken = msalToken;
        this.user = user;
        this.authenticated = authenticated;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public User getDetails() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String getPrincipal() {
        return msalToken;
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

    public static MsalAuthentication unauthenticated(String msalToken) {
        return new MsalAuthentication(msalToken, null, false);
    }

    public static MsalAuthentication authenticated(User user) {
        if (Objects.isNull(user)) {
            throw new BizException(HttpStatus.BAD_REQUEST, "User cannot be null.");
        }
        return new MsalAuthentication(null, user, true);
    }
}
