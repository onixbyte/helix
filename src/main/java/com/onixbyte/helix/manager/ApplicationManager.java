package com.onixbyte.helix.manager;

import com.onixbyte.helix.properties.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApplicationManager {

    private final ApplicationProperties applicationProperties;

    @Autowired
    public ApplicationManager(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    public String getDefaultEmail() {
        return applicationProperties.defaultEmail();
    }
}
