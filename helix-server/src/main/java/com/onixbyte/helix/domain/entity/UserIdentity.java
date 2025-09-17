package com.onixbyte.helix.domain.entity;

import com.onixbyte.helix.constant.IdentityProvider;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents an external identity mapping for a user.
 * <p>
 * This entity manages the relationship between internal user accounts and external identity
 * providers (such as OAuth providers, LDAP systems, or other authentication services). It enables
 * users to authenticate using external credentials while maintaining a consistent internal user
 * identity within the Helix system.
 *
 * @author zihluwang
 * @version 1.0
 * @since 1.0
 */
public class UserIdentity {

    /**
     * The identifier of the internal user account.
     * <p>
     * This field establishes the relationship between the external identity and the internal
     * user entity. It references the primary key of the User entity that this external identity
     * is associated with.
     */
    private Long userId;

    /**
     * The external identity provider.
     * <p>
     * This field specifies which external authentication system or service this identity mapping
     * represents, as defined by the {@link IdentityProvider} enumeration
     * (e.g., Google, Microsoft, LDAP, etc.).
     */
    private IdentityProvider provider;

    /**
     * The unique identifier from the external provider.
     * <p>
     * This field contains the user's unique identifier as provided by the external
     * authentication system. This could be an email address, username, or any other unique
     * identifier that the external provider uses.
     */
    private String externalId;

    /**
     * The timestamp when this identity mapping was created.
     * <p>
     * This field is automatically set when the user identity entity is first persisted and provides
     * audit information about when the external identity was first linked to the internal
     * user account.
     */
    private LocalDateTime createdAt;

    /**
     * The timestamp when this identity mapping was last updated.
     * <p>
     * This field is automatically updated whenever any changes are made to the user identity entity
     * and provides audit information about the most recent modification to the identity mapping.
     */
    private LocalDateTime updatedAt;

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public UserIdentity() {
    }

    public UserIdentity(Long userId, IdentityProvider provider, String externalId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.userId = userId;
        this.provider = provider;
        this.externalId = externalId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserIdentity that = (UserIdentity) o;
        return Objects.equals(userId, that.userId) && provider == that.provider && Objects.equals(externalId, that.externalId) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
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
     * Creates a new Builder instance for constructing UserIdentity objects.
     *
     * @return a new UserIdentityBuilder instance
     */
    public static UserIdentityBuilder builder() {
        return new UserIdentityBuilder();
    }

    /**
     * Builder class for constructing UserIdentity instances with a fluent API.
     * <p>
     * This builder provides a convenient way to construct UserIdentity objects with optional parameters,
     * following the Builder pattern for improved readability and maintainability.
     */
    public static class UserIdentityBuilder {
        private Long userId;
        private IdentityProvider provider;
        private String externalId;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

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

        public UserIdentityBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public UserIdentityBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        /**
         * Builds and returns a new UserIdentity instance with the configured properties.
         *
         * @return a new UserIdentity instance
         */
        public UserIdentity build() {
            return new UserIdentity(userId, provider, externalId, createdAt, updatedAt);
        }
    }
}
