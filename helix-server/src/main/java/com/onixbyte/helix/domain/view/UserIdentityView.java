package com.onixbyte.helix.domain.view;

import java.time.Instant;
import java.util.Objects;

/**
 * View object representing user identity information for presentation layer operations.
 * <p>
 * This view object encapsulates user identity data optimised for frontend display and user
 * interface operations. It provides a clean, presentation-focused interface for authentication and
 * identity management information whilst maintaining appropriate data visibility for
 * client-side operations.
 * <p>
 * The UserIdentityView contains information suitable for display in user interfaces, account
 * management panels, and API responses. It focuses on providing the essential data required for
 * identity management interfaces whilst excluding sensitive metadata and
 * implementation-specific details.
 * <p>
 * This object is designed to be serialised for API responses, used in frontend applications, and
 * serves as the primary data structure for identity-related user interface components.
 *
 * @author zihluwang
 * @see com.onixbyte.helix.domain.entity.UserIdentity
 * @see com.onixbyte.helix.domain.biz.BizUserIdentity
 * @since 1.0.0
 */
public class UserIdentityView {

    /**
     * The unique identifier of the user associated with this identity.
     * <p>
     * This field establishes the relationship between the identity record and the corresponding
     * user account, enabling frontend applications to associate identities with user profiles.
     */
    private Long userId;

    /**
     * The identity provider name or type.
     * <p>
     * This field identifies the authentication provider (e.g., "Google", "GitHub", "Microsoft") for
     * display in user interfaces, allowing users to understand which providers are linked to
     * their account.
     */
    private String provider;

    /**
     * The external identifier from the identity provider.
     * <p>
     * This field displays the external identifier for user reference, though sensitive portions may
     * be masked or truncated for security.
     */
    private String externalId;

    /**
     * Indicates whether this identity is currently active and valid.
     * <p>
     * This field allows frontend applications to display the status of each identity provider and
     * enable users to manage their connected authentication methods.
     */
    private Boolean active;

    /**
     * The timestamp when this identity was created.
     * <p>
     * This field provides users with information about when they first connected this identity
     * provider to their account.
     */
    private Instant createdAt;

    /**
     * The timestamp when this identity was last updated.
     * <p>
     * This field shows users when their identity information was last synchronised or modified.
     */
    private Instant updatedAt;

    /**
     * Default constructor for serialisation frameworks.
     */
    public UserIdentityView() {
    }

    /**
     * Constructs a new UserIdentityView with all fields specified.
     *
     * @param userId     the user identifier
     * @param provider   the identity provider name
     * @param externalId the external identifier
     * @param active     whether the identity is active
     * @param createdAt  the creation timestamp
     * @param updatedAt  the last update timestamp
     */
    public UserIdentityView(Long userId, String provider, String externalId,
                            Boolean active, Instant createdAt, Instant updatedAt) {
        this.userId = userId;
        this.provider = provider;
        this.externalId = externalId;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserIdentityView that = (UserIdentityView) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(provider, that.provider) &&
                Objects.equals(externalId, that.externalId) &&
                Objects.equals(active, that.active) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, provider, externalId, active, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "UserIdentityView{" +
                "userId=" + userId +
                ", provider='" + provider + '\'' +
                ", externalId='" + externalId + '\'' +
                ", active=" + active +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    /**
     * Creates a new builder instance for constructing UserIdentityView objects.
     *
     * @return a new UserIdentityViewBuilder instance
     */
    public static UserIdentityViewBuilder builder() {
        return new UserIdentityViewBuilder();
    }

    /**
     * Builder class for constructing UserIdentityView instances with a fluent interface.
     */
    public static class UserIdentityViewBuilder {
        private Long userId;
        private String provider;
        private String externalId;
        private Boolean active;
        private Instant createdAt;
        private Instant updatedAt;

        /**
         * Private constructor to enforce builder pattern usage.
         */
        private UserIdentityViewBuilder() {
        }

        public UserIdentityViewBuilder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public UserIdentityViewBuilder provider(String provider) {
            this.provider = provider;
            return this;
        }

        public UserIdentityViewBuilder externalId(String externalId) {
            this.externalId = externalId;
            return this;
        }

        public UserIdentityViewBuilder active(Boolean active) {
            this.active = active;
            return this;
        }

        public UserIdentityViewBuilder createdAt(Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public UserIdentityViewBuilder updatedAt(Instant updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        /**
         * Builds and returns a new UserIdentityView instance with the configured values.
         *
         * @return a new UserIdentityView instance
         */
        public UserIdentityView build() {
            return new UserIdentityView(userId, provider, externalId, active, createdAt, updatedAt);
        }
    }
}
