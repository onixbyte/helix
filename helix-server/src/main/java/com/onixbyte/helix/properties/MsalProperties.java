package com.onixbyte.helix.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Microsoft Entra ID properties, used to assist authenticate users who are linked to MSAL.
 *
 * @author zihluwang
 */
@ConfigurationProperties(prefix = "app.authentication.msal")
public class MsalProperties {

    private String clientId;

    private String tenantId;

    public MsalProperties() {
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
}
