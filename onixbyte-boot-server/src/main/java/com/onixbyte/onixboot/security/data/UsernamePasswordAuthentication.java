package com.onixbyte.onixboot.security.data;

import com.onixbyte.onixboot.entities.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class UsernamePasswordAuthentication implements Authentication, CredentialsContainer {

    private String username;

    private String password;

    private User user;

    private boolean authenticated;

    public UsernamePasswordAuthentication(
            String username,
            String password,
            User user,
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
    public User getDetails() {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static UsernamePasswordAuthentication unauthenticated(String username, String password) {
        return new UsernamePasswordAuthentication(username, password, null, false);
    }

    public static UsernamePasswordAuthentication authenticated(User user) {
        if (Objects.isNull(user)) {
            throw new IllegalStateException("Authenticated user cannot be null.");
        }

        return new UsernamePasswordAuthentication(user.getUsername(), null, user, true);
    }
}
