package com.onixbyte.helix.security.data;

import com.onixbyte.helix.domain.biz.BizUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class UsernamePasswordAuthentication implements Authentication, CredentialsContainer {

    private String username;

    private String password;

    private BizUser user;

    private boolean authenticated;

    public UsernamePasswordAuthentication(
            String username,
            String password,
            BizUser user,
            boolean authenticated
    ) {
        this.username = username;
        this.password = password;
        this.user = user;
        this.authenticated = authenticated;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getCredentials() {
        return password;
    }

    @Override
    public BizUser getDetails() {
        return user;
    }

    @Override
    public String getPrincipal() {
        return username;
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
        return user.getFullName();
    }

    @Override
    public void eraseCredentials() {
        this.password = null;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setBizUser(BizUser user) {
        this.user = user;
    }

    public static UsernamePasswordAuthentication unauthenticated(String username, String password) {
        return new UsernamePasswordAuthentication(username, password, null, false);
    }

    public static UsernamePasswordAuthentication authenticated(BizUser user) {
        if (Objects.isNull(user)) {
            throw new IllegalStateException("Authenticated user cannot be null.");
        }

        return new UsernamePasswordAuthentication(user.getUsername(), null, user, true);
    }
}
