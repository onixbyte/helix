/*
 * Copyright (C) 2024 OnixByte.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.onixbyte.helix.domain.biz;

import java.time.Instant;
import java.util.Objects;

/**
 * Business object representing user identity information within the Helix application.
 * <p>
 * This business object encapsulates user identity data for business logic operations, providing a
 * clean interface for authentication and identity management operations within the service layer.
 * It serves as a data transfer object between different application layers whilst maintaining the
 * integrity of user identity information.
 * <p>
 * The BizUserIdentity contains all information necessary for business operations related to
 * user authentication, external identity provider integration, and identity verification processes.
 * It supports multi-provider authentication scenarios and federated identity management.
 * <p>
 * This object is designed to be used in business logic implementations, service layer operations,
 * and as a foundation for creating view objects or API responses that require user
 * identity information.
 *
 * @author zihluwang
 * @see com.onixbyte.helix.domain.entity.UserIdentity
 * @since 1.0.0
 */
public class BizUserIdentity {

    /**
     * The unique identifier of the user associated with this identity.
     * <p>
     * This field establishes the relationship between the identity record and the corresponding
     * user account in the system.
     */
    private Long userId;

    /**
     * The identity provider name or type.
     * <p>
     * This field identifies the authentication provider or system that manages this identity
     * (e.g., "google", "github", "ldap"). It enables support for multiple authentication providers.
     */
    private String provider;

    /**
     * The external identifier from the identity provider.
     * <p>
     * This field stores the unique identifier assigned by the external authentication provider,
     * enabling correlation between local user accounts and external identities.
     */
    private String externalId;

    /**
     * Additional metadata or attributes from the identity provider.
     * <p>
     * This field can store provider-specific information such as profile data, tokens, or other
     * relevant identity attributes in JSON or structured format.
     */
    private String metadata;

    /**
     * Indicates whether this identity is currently active and valid.
     * <p>
     * This field allows for deactivation of specific identity providers without removing the
     * identity record entirely, supporting temporary access restrictions.
     */
    private Boolean active;

    /**
     * The timestamp when this identity was created.
     * <p>
     * This field tracks when the identity was first established, which is useful for audit trails
     * and account history.
     */
    private Instant createdAt;

    /**
     * The timestamp when this identity was last updated.
     * <p>
     * This field tracks when the identity information was last modified, useful for synchronisation
     * and audit purposes.
     */
    private Instant updatedAt;

    /**
     * Default constructor for serialisation frameworks.
     */
    public BizUserIdentity() {
    }

    /**
     * Constructs a new BizUserIdentity with all fields specified.
     *
     * @param userId     the user identifier
     * @param provider   the identity provider name
     * @param externalId the external identifier
     * @param metadata   the additional metadata
     * @param active     the active status
     * @param createdAt  the creation timestamp
     * @param updatedAt  the last update timestamp
     */
    public BizUserIdentity(Long userId, String provider, String externalId,
                           String metadata, Boolean active, Instant createdAt, Instant updatedAt) {
        this.userId = userId;
        this.provider = provider;
        this.externalId = externalId;
        this.metadata = metadata;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * Gets the unique identifier of the user associated with this identity.
     *
     * @return the user ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Sets the unique identifier of the user associated with this identity.
     *
     * @param userId the user ID to set
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Gets the identity provider name or type.
     *
     * @return the provider name
     */
    public String getProvider() {
        return provider;
    }

    /**
     * Sets the identity provider name or type.
     *
     * @param provider the provider name to set
     */
    public void setProvider(String provider) {
        this.provider = provider;
    }

    /**
     * Gets the external identifier from the identity provider.
     *
     * @return the external ID
     */
    public String getExternalId() {
        return externalId;
    }

    /**
     * Sets the external identifier from the identity provider.
     *
     * @param externalId the external ID to set
     */
    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    /**
     * Gets the additional metadata or attributes from the identity provider.
     *
     * @return the metadata
     */
    public String getMetadata() {
        return metadata;
    }

    /**
     * Sets the additional metadata or attributes from the identity provider.
     *
     * @param metadata the metadata to set
     */
    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    /**
     * Gets whether this identity is currently active and valid.
     *
     * @return the active status
     */
    public Boolean getActive() {
        return active;
    }

    /**
     * Sets whether this identity is currently active and valid.
     *
     * @param active the active status to set
     */
    public void setActive(Boolean active) {
        this.active = active;
    }

    /**
     * Gets the timestamp when this identity was created.
     *
     * @return the creation timestamp
     */
    public Instant getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the timestamp when this identity was created.
     *
     * @param createdAt the creation timestamp to set
     */
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets the timestamp when this identity was last updated.
     *
     * @return the last update timestamp
     */
    public Instant getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets the timestamp when this identity was last updated.
     *
     * @param updatedAt the last update timestamp to set
     */
    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param obj the reference object with which to compare
     * @return true if this object is the same as the obj argument; false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        BizUserIdentity that = (BizUserIdentity) obj;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(provider, that.provider) &&
                Objects.equals(externalId, that.externalId) &&
                Objects.equals(metadata, that.metadata) &&
                Objects.equals(active, that.active) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt);
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(userId, provider, externalId, metadata, active, createdAt, updatedAt);
    }

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return "BizUserIdentity{" +
                "userId=" + userId +
                ", provider='" + provider + '\'' +
                ", externalId='" + externalId + '\'' +
                ", metadata='" + metadata + '\'' +
                ", active=" + active +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    /**
     * Creates a new builder instance for constructing BizUserIdentity instances.
     *
     * @return a new Builder instance
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing BizUserIdentity instances using the builder pattern.
     * <p>
     * This builder provides a fluent interface for creating BizUserIdentity instances with
     * optional parameters, improving code readability and maintainability.
     */
    public static class Builder {
        private Long userId;
        private String provider;
        private String externalId;
        private String metadata;
        private Boolean active;
        private Instant createdAt;
        private Instant updatedAt;

        /**
         * Private constructor to enforce builder pattern usage.
         */
        private Builder() {
        }

        /**
         * Sets the unique identifier of the user associated with this identity.
         *
         * @param userId the user ID
         * @return this Builder instance for method chaining
         */
        public Builder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        /**
         * Sets the identity provider name or type.
         *
         * @param provider the provider name
         * @return this Builder instance for method chaining
         */
        public Builder provider(String provider) {
            this.provider = provider;
            return this;
        }

        /**
         * Sets the external identifier from the identity provider.
         *
         * @param externalId the external ID
         * @return this Builder instance for method chaining
         */
        public Builder externalId(String externalId) {
            this.externalId = externalId;
            return this;
        }

        /**
         * Sets the additional metadata or attributes from the identity provider.
         *
         * @param metadata the metadata
         * @return this Builder instance for method chaining
         */
        public Builder metadata(String metadata) {
            this.metadata = metadata;
            return this;
        }

        /**
         * Sets whether this identity is currently active and valid.
         *
         * @param active the active status
         * @return this Builder instance for method chaining
         */
        public Builder active(Boolean active) {
            this.active = active;
            return this;
        }

        /**
         * Sets the timestamp when this identity was created.
         *
         * @param createdAt the creation timestamp
         * @return this Builder instance for method chaining
         */
        public Builder createdAt(Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        /**
         * Sets the timestamp when this identity was last updated.
         *
         * @param updatedAt the last update timestamp
         * @return this Builder instance for method chaining
         */
        public Builder updatedAt(Instant updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        /**
         * Builds and returns a new BizUserIdentity instance with the configured values.
         *
         * @return a new BizUserIdentity instance
         */
        public BizUserIdentity build() {
            return new BizUserIdentity(userId, provider, externalId, metadata, active, createdAt, updatedAt);
        }
    }
}
