package com.onixbyte.helix.security.data;

import com.onixbyte.helix.domain.biz.BizUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

/**
 * Microsoft authentication.
 *
 * @author zihluwang
 */
public class MsalAuthentication implements Authentication {

    /**
     * Identity tokens issued by Microsoft to users.
     */
    private final String msalToken;

    /**
     * Users who receive identity tokens issued by Microsoft.
     */
    private BizUser user;

    /**
     * A flag indicating whether the user has completed identity authentication.
     */
    private boolean authenticated;

    public MsalAuthentication(
            String msalToken,
            BizUser user,
            boolean authenticated
    ) {
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
    public BizUser getDetails() {
        return user;
    }

    public void setUser(BizUser user) {
        this.user = user;
    }

    public BizUser getUser() {
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
        return user.getFullName();
    }

    public static MsalAuthentication unauthenticated(String msalToken) {
        return new MsalAuthentication(msalToken, null, false);
    }
}
