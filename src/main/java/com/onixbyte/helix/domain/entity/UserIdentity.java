package com.onixbyte.helix.domain.entity;

import com.onixbyte.helix.enumeration.IdentityProvider;
import com.onixbyte.helix.domain.entity.embeddable.UserIdentityId;
import jakarta.persistence.*; // 导入 Jakarta Persistence API
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
@Entity
@Table(name = "user_identities")
public class UserIdentity {

    /**
     * The composite primary key for the entity, composed of userId, provider, and externalId.
     */
    @EmbeddedId
    private UserIdentityId id;

    /**
     * The timestamp when this identity mapping was created.
     */
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * The timestamp when this identity mapping was last updated.
     */
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    // --- JPA Lifecycle Callbacks for Auditing ---

    @PrePersist
    protected void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
        if (this.updatedAt == null) {
            this.updatedAt = LocalDateTime.now();
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }


    // --- Getters and Setters (Delegating to EmbeddedId) ---

    /**
     * Gets the identifier of the internal user account from the composite primary key.
     * @return the user ID
     */
    public Long getUserId() {
        return this.id != null ? this.id.getUserId() : null;
    }

    /**
     * Sets the identifier of the internal user account within the composite primary key.
     * @param userId the user ID
     */
    public void setUserId(Long userId) {
        if (this.id == null) this.id = new UserIdentityId();
        this.id.setUserId(userId);
    }

    /**
     * Gets the external identity provider from the composite primary key.
     * @return the provider
     */
    public IdentityProvider getProvider() {
        return this.id != null ? this.id.getProvider() : null;
    }

    /**
     * Sets the external identity provider within the composite primary key.
     * @param provider the provider
     */
    public void setProvider(IdentityProvider provider) {
        if (this.id == null) this.id = new UserIdentityId();
        this.id.setProvider(provider);
    }

    /**
     * Gets the unique identifier from the external provider from the composite primary key.
     * @return the external ID
     */
    public String getExternalId() {
        return this.id != null ? this.id.getExternalId() : null;
    }

    /**
     * Sets the unique identifier from the external provider within the composite primary key.
     * @param externalId the external ID
     */
    public void setExternalId(String externalId) {
        if (this.id == null) this.id = new UserIdentityId();
        this.id.setExternalId(externalId);
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

    // --- Constructors (Adjusted for EmbeddedId) ---

    public UserIdentity() {
        this.id = new UserIdentityId(); // Initialize ID object for safety
    }

    public UserIdentity(Long userId, IdentityProvider provider, String externalId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = new UserIdentityId(userId, provider, externalId);
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // --- Overrides (Simplified to use the Id object for entity equality) ---

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserIdentity that = (UserIdentity) o;
        return Objects.equals(id, that.id); // Entity equality based on primary key
    }

    @Override
    public int hashCode() {
        return Objects.hash(id); // Hash code based on primary key
    }

    @Override
    public String toString() {
        return "UserIdentity{" +
                "userId=" + getUserId() +
                ", provider=" + getProvider() +
                ", externalId='" + getExternalId() + '\'' +
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