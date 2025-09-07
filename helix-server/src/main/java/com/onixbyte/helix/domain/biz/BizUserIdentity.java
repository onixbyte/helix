package com.onixbyte.helix.domain.biz;

import com.onixbyte.helix.constant.IdentityProvider;

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

    public static BizUserIdentityBuilder builder() {
        return new BizUserIdentityBuilder();
    }

    public static class BizUserIdentityBuilder {
        private String userId;
        private IdentityProvider provider;
        private String externalId;

        private BizUserIdentityBuilder() {
        }

        public BizUserIdentityBuilder userId(String userId) {
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

        public BizUserIdentity build() {
            return new BizUserIdentity(userId, provider, externalId);
        }
    }
}
