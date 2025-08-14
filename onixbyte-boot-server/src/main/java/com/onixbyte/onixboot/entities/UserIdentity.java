package com.onixbyte.onixboot.entities;

import com.onixbyte.onixboot.enums.IdentityProvider;

import java.time.Instant;
import java.util.Objects;

public class UserIdentity {

    private Long userId;

    private IdentityProvider provider;

    private String externalId;

    private Instant createdAt;

    private Instant updatedAt;

    public UserIdentity() {
    }

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
}
