package com.onixbyte.helix.domain.biz;

import com.onixbyte.helix.enums.IdentityProvider;

import java.io.Serializable;
import java.util.Objects;

public class BizUserIdentity implements Serializable {

    private String userId;

    private IdentityProvider provider;

    private String externalId;

    public BizUserIdentity() {
    }

    public BizUserIdentity(String userId, IdentityProvider provider, String externalId) {
        this.userId = userId;
        this.provider = provider;
        this.externalId = externalId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BizUserIdentity that = (BizUserIdentity) o;
        return Objects.equals(userId, that.userId) &&
                provider == that.provider &&
                Objects.equals(externalId, that.externalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, provider, externalId);
    }

    @Override
    public String toString() {
        return "BizUserIdentity{" +
                "userId=" + userId +
                ", provider='" + provider + '\'' +
                ", externalId='" + externalId + '\'' +
                '}';
    }
}
