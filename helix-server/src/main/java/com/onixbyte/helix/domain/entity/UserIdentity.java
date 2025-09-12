package com.onixbyte.helix.domain.entity;

import com.onixbyte.helix.constant.IdentityProvider;

import java.time.Instant;
import java.util.Objects;

/**
 * Entity representing user identity associations with external identity providers.
 * <p>
 * This entity manages the relationship between internal users and external identity providers,
 * enabling federated authentication and single sign-on (SSO) capabilities. Each user identity
 * record links a local user account to an external identity from providers such as OAuth services,
 * LDAP directories, or other authentication systems.
 * <p>
 * The user identity system supports multiple authentication strategies by allowing users to have
 * multiple identity associations. This enables flexible authentication workflows where users can
 * authenticate through different providers whilst maintaining a single internal user profile.
 * <p>
 * Each identity association contains the external identifier used by the identity provider, the
 * provider type, and metadata about when the association was created and last updated.
 * This information is crucial for maintaining synchronisation with external systems and auditing
 * authentication events.
 *
 * @author zihluwang
 * @see User
 * @see IdentityProvider
 * @since 1.0.0
 */
public class UserIdentity {

    /**
     * The identifier of the user this identity belongs to.
     * <p>
     * This field references {@link User#getId()} and establishes the association between the
     * external identity and the internal user account.
     */
    private Long userId;

    /**
     * The identity provider that manages this external identity.
     * <p>
     * This field specifies which authentication system or service is responsible for validating
     * this identity, such as OAuth providers, LDAP directories, or local authentication.
     */
    private IdentityProvider provider;

    /**
     * The external identifier used by the identity provider.
     * <p>
     * This field contains the unique identifier assigned by the external identity provider to
     * represent this user. The format and structure of this identifier depends on the specific
     * provider implementation.
     */
    private String externalId;

    /**
     * The timestamp when this identity association was created.
     */
    private Instant createdAt;

    /**
     * The timestamp when this identity association was last updated.
     */
    private Instant updatedAt;

    /**
     * Default constructor for JPA and serialisation frameworks.
     */
    public UserIdentity() {
    }

    /**
     * Constructs a new UserIdentity with all fields specified.
     *
     * @param userId     the identifier of the associated user
     * @param provider   the identity provider managing this identity
     * @param externalId the external identifier from the provider
     * @param createdAt  the creation timestamp
     * @param updatedAt  the last update timestamp
     */
    public UserIdentity(Long userId, IdentityProvider provider, String externalId, Instant createdAt, Instant updatedAt) {
        this.userId = userId;
        this.provider = provider;
        this.externalId = externalId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public IdentityProvider getProvider() {
        return provider;
    }

    public void setProvider(IdentityProvider provider) {
        this.provider = provider;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserIdentity that = (UserIdentity) o;
        return Objects.equals(userId, that.userId) &&
                provider == that.provider &&
                Objects.equals(externalId, that.externalId) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, provider, externalId, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "UserIdentity{" +
                "userId=" + userId +
                ", provider=" + provider +
                ", externalId='" + externalId + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    /**
     * Creates a new builder instance for constructing UserIdentity objects.
     *
     * @return a new {@link UserIdentityBuilder} instance
     */
    public static UserIdentityBuilder builder() {
        return new UserIdentityBuilder();
    }

    /**
     * Builder class for constructing {@link UserIdentity} instances.
     * <p>
     * This builder provides a fluent interface for setting user identity properties and ensures
     * consistent object construction.
     */
    public static class UserIdentityBuilder {
        private Long userId;
        private IdentityProvider provider;
        private String externalId;
        private Instant createdAt;
        private Instant updatedAt;

        /**
         * Private constructor to enforce builder pattern usage.
         */
        private UserIdentityBuilder() {
        }

        public UserIdentityBuilder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public UserIdentityBuilder provider(IdentityProvider provider) {
            this.provider = provider;
            return this;
        }

        public UserIdentityBuilder externalId(String externalId) {
            this.externalId = externalId;
            return this;
        }

        public UserIdentityBuilder createdAt(Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public UserIdentityBuilder updatedAt(Instant updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        /**
         * Builds and returns a new UserIdentity instance with the configured properties.
         *
         * @return a new {@link UserIdentity} instance
         */
        public UserIdentity build() {
            return new UserIdentity(userId, provider, externalId, createdAt, updatedAt);
        }
    }
}
