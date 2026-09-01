package com.onixbyte.helix.security.authentication;

import com.onixbyte.helix.domain.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;
import java.util.List;

public class UsernamePasswordAuthentication implements Authentication, CredentialsContainer {
    private final String username;
    private String password;
    private boolean authenticated;
    private User user;
    private List<? extends GrantedAuthority> authorities;

    private UsernamePasswordAuthentication(String username, String password, boolean authenticated, User user, List<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authenticated = authenticated;
        this.user = user;
        this.authorities = authorities;
    }

    public static UsernamePasswordAuthentication unauthenticated(String username, String password) {
        return new UsernamePasswordAuthentication(username, password, false, null, List.of());
    }

    public static UsernamePasswordAuthentication authenticated(User user, List<? extends GrantedAuthority> authorities) {
        return new UsernamePasswordAuthentication(user.getUsername(), null, true, user, authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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
        return username;
    }

    @Override
    public void eraseCredentials() {
        this.password = null;
    }

    public void setDetails(User user) {
        this.user = user;
    }

    public void setAuthorities(List<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
