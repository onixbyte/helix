package com.onixbyte.helix.domain.view;

import com.onixbyte.helix.constant.IdentityProvider;
import com.onixbyte.helix.domain.entity.UserIdentity;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * View object for UserIdentity entity.
 * <p>
 * This class represents a view of the UserIdentity entity, providing a data transfer object
 * for external identity mapping information in the Helix system.
 *
 * @author zihluwang
 * @version 1.0
 * @since 1.0
 */
public class UserIdentityView {

    /**
     * The identifier of the internal user account.
     */
    private Long userId;

    /**
     * The external identity provider.
     */
    private IdentityProvider provider;

    /**
     * The unique identifier from the external provider.
     */
    private String externalId;

    /**
     * The timestamp when this identity mapping was created.
     */
    private LocalDateTime createdAt;

    /**
     * The timestamp when this identity mapping was last updated.
     */
    private LocalDateTime updatedAt;

    /**
     * Default constructor.
     */
    public UserIdentityView() {
    }

    /**
     * Constructor with all fields.
     */
    public UserIdentityView(Long userId, IdentityProvider provider, String externalId,
                           LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.userId = userId;
        this.provider = provider;
        this.externalId = externalId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * Creates a UserIdentityView from a UserIdentity entity.
     *
     * @param userIdentity the UserIdentity entity
     * @return the UserIdentityView object
     */
    public static UserIdentityView fromEntity(UserIdentity userIdentity) {
        if (userIdentity == null) {
            return null;
        }
        return new UserIdentityView(
                userIdentity.getUserId(),
                userIdentity.getProvider(),
                userIdentity.getExternalId(),
                userIdentity.getCreatedAt(),
                userIdentity.getUpdatedAt()
        );
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserIdentityView that = (UserIdentityView) o;
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
        return "UserIdentityView{" +
                "userId=" + userId +
                ", provider=" + provider +
                ", externalId='" + externalId + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    /**
     * Creates a new builder instance.
     *
     * @return a new UserIdentityViewBuilder
     */
    public static UserIdentityViewBuilder builder() {
        return new UserIdentityViewBuilder();
    }

    /**
     * Builder class for UserIdentityView.
     */
    public static class UserIdentityViewBuilder {
        private Long userId;
        private IdentityProvider provider;
        private String externalId;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        private UserIdentityViewBuilder() {
        }

        public UserIdentityViewBuilder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public UserIdentityViewBuilder provider(IdentityProvider provider) {
            this.provider = provider;
            return this;
        }

        public UserIdentityViewBuilder externalId(String externalId) {
            this.externalId = externalId;
            return this;
        }

        public UserIdentityViewBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public UserIdentityViewBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public UserIdentityView build() {
            return new UserIdentityView(userId, provider, externalId, createdAt, updatedAt);
        }
    }
}