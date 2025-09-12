package com.onixbyte.helix.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for Microsoft Authentication Library (MSAL) integration.
 * <p>
 * This class provides configuration binding for Microsoft Entra ID (formerly Azure Active Directory)
 * authentication settings. It enables the Helix application to authenticate users through
 * Microsoft's identity platform using the MSAL library, supporting single sign-on (SSO)
 * and enterprise identity management scenarios.
 * <p>
 * <strong>MSAL Integration:</strong> The Microsoft Authentication Library provides a unified
 * way to authenticate users and acquire tokens for accessing Microsoft services and APIs.
 * This configuration class specifically handles the core identifiers required for establishing
 * a connection with Microsoft Entra ID.
 * <p>
 * <strong>Configuration Example:</strong>
 * <pre>{@code
 * app:
 *   authentication:
 *     msal:
 *       client-id: "12345678-1234-1234-1234-123456789012"
 *       tenant-id: "87654321-4321-4321-4321-210987654321"
 * }</pre>
 * <p>
 * <strong>Security Note:</strong> The client ID is considered public information and can be
 * safely stored in configuration files. However, ensure that any client secrets (if used)
 * are stored securely and not exposed in version control or logs.
 * <p>
 * All properties are prefixed with {@code app.authentication.msal} and are automatically
 * bound by Spring Boot's configuration property mechanism.
 *
 * @author zihluwang
 * @since 1.0.0
 * @see ConfigurationProperties
 * @see <a href="https://docs.microsoft.com/en-us/azure/active-directory/develop/msal-overview">MSAL Overview</a>
 * @see <a href="https://docs.microsoft.com/en-us/entra/identity-platform/">Microsoft Entra ID Platform</a>
 */
@ConfigurationProperties(prefix = "app.authentication.msal")
public class MsalProperties {

    /**
     * The application (client) ID assigned to the application when registered in Microsoft Entra ID.
     * <p>
     * This unique identifier is used by Microsoft Entra ID to identify the application
     * during authentication flows. It is a GUID that is generated when the application
     * is registered in the Microsoft Entra ID portal.
     * <p>
     * Example: {@code "12345678-1234-1234-1234-123456789012"}
     */
    private String clientId;

    /**
     * The directory (tenant) ID of the Microsoft Entra ID tenant where the application is registered.
     * <p>
     * This identifier specifies which Microsoft Entra ID tenant should be used for authentication.
     * It can be either the tenant ID (GUID) or a verified domain name of the tenant.
     * Use {@code "common"} to allow users from any Microsoft Entra ID tenant,
     * or {@code "organizations"} to allow only work and school accounts.
     * <p>
     * Example: {@code "87654321-4321-4321-4321-210987654321"}
     */
    private String tenantId;

    /**
     * Constructs a new {@code MsalProperties} instance.
     * <p>
     * This default constructor is used by Spring Boot's configuration property
     * binding mechanism to create and populate the properties bean.
     */
    public MsalProperties() {
    }

    /**
     * Retrieves the application (client) ID for Microsoft Entra ID authentication.
     *
     * @return the client ID as a string, or {@code null} if not configured
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * Sets the application (client) ID for Microsoft Entra ID authentication.
     *
     * @param clientId the client ID to set, typically a GUID string
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    /**
     * Retrieves the directory (tenant) ID for Microsoft Entra ID authentication.
     *
     * @return the tenant ID as a string, or {@code null} if not configured
     */
    public String getTenantId() {
        return tenantId;
    }

    /**
     * Sets the directory (tenant) ID for Microsoft Entra ID authentication.
     *
     * @param tenantId the tenant ID to set, can be a GUID, domain name, or special value like "common"
     */
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
}
