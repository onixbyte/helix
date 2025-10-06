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
 * @param tenantId directory (tenant) ID of the Microsoft Entra ID tenant where the application
 *                 is registered
 * @param clientId application (client) ID assigned to the application when registered in
 *                 Microsoft Entra ID
 * @author zihluwang
 * @see ConfigurationProperties
 * @see <a href="https://docs.microsoft.com/en-us/azure/active-directory/develop/msal-overview">MSAL Overview</a>
 * @see <a href="https://docs.microsoft.com/en-us/entra/identity-platform/">Microsoft Entra ID Platform</a>
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = "app.authentication.msal")
public record MsalProperties(
        String tenantId,
        String clientId
) {
}
