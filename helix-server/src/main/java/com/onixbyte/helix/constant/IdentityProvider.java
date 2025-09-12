package com.onixbyte.helix.constant;

/**
 * Enumeration of supported identity providers for user authentication.
 * <p>
 * This enumeration defines the various identity providers that the Helix application supports for
 * user authentication and authorisation. Each provider represents a different authentication
 * mechanism or external identity service that can be used to verify user credentials and establish
 * user sessions.
 * <p>
 * The application supports both local authentication (using internal user database) and external
 * identity providers (such as Microsoft Entra ID) to provide flexible authentication options for
 * different deployment scenarios and organisational requirements.
 *
 * @author zihluwang
 * @since 1.0.0
 * @see com.onixbyte.helix.config.AuthenticationConfiguration
 */
public enum IdentityProvider {

    /**
     * Local identity provider using the application's internal user database.
     * <p>
     * This provider authenticates users against locally stored credentials, typically using
     * username/email and password combinations. User accounts are managed entirely within the Helix
     * application's database.
     */
    LOCAL,

    /**
     * Microsoft Entra ID (formerly Azure Active Directory) identity provider.
     * <p>
     * This provider enables authentication through Microsoft's cloud-based identity and access
     * management service. Users authenticate using their organisational Microsoft accounts,
     * supporting features such as single sign-on (SSO) and multi-factor authentication (MFA).
     */
    MICROSOFT_ENTRA_ID
}
