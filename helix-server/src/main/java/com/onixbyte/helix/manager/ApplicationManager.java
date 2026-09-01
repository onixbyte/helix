package com.onixbyte.helix.manager;

import com.onixbyte.helix.enumeration.ApplicationMode;
import com.onixbyte.helix.properties.ApplicationProperties;
import com.onixbyte.helix.properties.AuthenticationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ApplicationManager {

    private final ApplicationProperties applicationProperties;
    private final AuthenticationProperties authenticationProperties;

    @Autowired
    public ApplicationManager(ApplicationProperties applicationProperties, AuthenticationProperties authenticationProperties) {
        this.applicationProperties = applicationProperties;
        this.authenticationProperties = authenticationProperties;
    }

    public String getDefaultEmail() {
        return applicationProperties.defaultEmail();
    }

    public String getExternalHost() {
        return applicationProperties.externalHost();
    }

    public boolean isSslEnabled() {
        return Optional.ofNullable(authenticationProperties.sslEnabled())
                .orElse(false);
    }

    public boolean isSecureCookieEnabled() {
        return Optional.ofNullable(authenticationProperties.secureCookieEnabled())
                .orElse(false);
    }

    public ApplicationMode getApplicationMode() {
        return applicationProperties.mode();
    }
}
