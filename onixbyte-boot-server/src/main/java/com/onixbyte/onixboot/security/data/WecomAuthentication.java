package com.onixbyte.onixboot.security.data;

import com.onixbyte.onixboot.exception.BizException;
import com.onixbyte.onixboot.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * Wecom authentication.
 *
 * @author zihluwang
 */
public class WecomAuthentication implements Authentication {

    /**
     * One-Time authentication code issued by Wecom to users.
     */
    private final String code;

    /**
     * Users who receive One-Time authentication code issued by Microsoft.
     */
    private User user;

    /**
     * A flag indicating whether the user has completed identity authentication.
     */
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

    public WecomAuthentication(
            String code,
            User user,
            boolean authenticated
    ) {
        this.code = code;
        this.user = user;
        this.authenticated = authenticated;
    }

    public static WecomAuthentication unauthenticated(String code) {
        return new WecomAuthentication(code, null, false);
    }
}
