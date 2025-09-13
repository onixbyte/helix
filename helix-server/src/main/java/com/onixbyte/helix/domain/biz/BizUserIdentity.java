package com.onixbyte.helix.domain.biz;

import com.onixbyte.helix.constant.IdentityProvider;
import com.onixbyte.helix.domain.entity.UserIdentity;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Business object representing an external identity mapping for a user.
 * <p>
 * This class serves as a data transfer object for user identity-related operations, providing a
 * clean interface for business logic while maintaining separation from the underlying
 * entity representation.
 *
 * @author zihluwang
 * @version 1.0
 * @since 1.0
 */
public class BizUserIdentity {

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
     * The timestamp when this user identity record was created.
     */
    private LocalDateTime createdAt;

    /**
     * The timestamp when this user identity record was last updated.
     */
    private LocalDateTime updatedAt;

    /**
     * Default constructor.
     */
    public BizUserIdentity() {
    }

    /**
     * Constructs a new BizUserIdentity with all fields specified.
     *
     * @param userId     the identifier of the associated user
     * @param provider   the identity provider managing this identity
     * @param externalId the external identifier from the provider
     * @param createdAt  the creation timestamp
     * @param updatedAt  the last update timestamp
     */
    public BizUserIdentity(Long userId, IdentityProvider provider, String externalId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.userId = userId;
        this.provider = provider;
        this.externalId = externalId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * Creates a BizUserIdentity from a UserIdentity entity.
     *
     * @param userIdentity the UserIdentity entity
     * @return a new BizUserIdentity instance
     */
    public static BizUserIdentity fromEntity(UserIdentity userIdentity) {
        if (userIdentity == null) {
            return null;
        }
        return new BizUserIdentity(
                userIdentity.getUserId(),
                userIdentity.getProvider(),
                userIdentity.getExternalId(),
                userIdentity.getCreatedAt(),
                userIdentity.getUpdatedAt()
        );
    }

    /**
     * Converts this BizUserIdentity to a UserIdentity entity.
     *
     * @return a new UserIdentity entity
     */
    public UserIdentity asEntity() {
        return UserIdentity.builder()
                .userId(userId)
                .provider(provider)
                .externalId(externalId)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
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
        if (o == null || getClass() != o.getClass()) return false;
        BizUserIdentity that = (BizUserIdentity) o;
        return Objects.equals(userId, that.userId) && provider == that.provider &&
                Objects.equals(externalId, that.externalId) &&
                Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, provider, externalId, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "BizUserIdentity{" +
                "userId=" + userId +
                ", provider=" + provider +
                ", externalId='" + externalId + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    /**
     * Creates a new Builder instance for constructing BizUserIdentity objects.
     *
     * @return a new BizUserIdentityBuilder instance
     */
    public static BizUserIdentityBuilder builder() {
        return new BizUserIdentityBuilder();
    }

    /**
     * Builder class for constructing BizUserIdentity instances with a fluent API.
     */
    public static class BizUserIdentityBuilder {
        private Long userId;
        private IdentityProvider provider;
        private String externalId;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        private BizUserIdentityBuilder() {
        }

        public BizUserIdentityBuilder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public BizUserIdentityBuilder provider(IdentityProvider provider) {
            this.provider = provider;
            return this;
        }

        public BizUserIdentityBuilder externalId(String externalId) {
            this.externalId = externalId;
            return this;
        }

        public BizUserIdentityBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public BizUserIdentityBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        /**
         * Builds and returns a new BizUserIdentity instance with the configured properties.
         *
         * @return a new BizUserIdentity instance
         */
        public BizUserIdentity build() {
            return new BizUserIdentity(userId, provider, externalId, createdAt, updatedAt);
        }
    }
}