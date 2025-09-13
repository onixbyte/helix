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
 * Business object representing role-authority associations within the Helix application.
 *
 * <p>This business object encapsulates role-authority relationship data for business logic
 * operations, providing a clean interface for permission assignment operations within
 * the service layer. It serves as a data transfer object between different application
 * layers whilst maintaining the integrity of role-authority association information.
 *
 * <p>The BizRoleAuthority contains all information necessary for business operations
 * related to role-based access control (RBAC), including role and authority identifiers
 * and administrative metadata. It supports permission management functionality and
 * access control operations.
 *
 * <p>This object is designed to be used in business logic implementations, service layer
 * operations, and as a foundation for creating view objects or API responses that
 * require role-authority association information.
 *
 * @see com.onixbyte.helix.domain.entity.RoleAuthority
 * @author zihluwang
 * @since 1.0.0
 */
public class BizRoleAuthority {

    /**
     * The unique identifier of the role in this association.
     *
     * <p>This field references the role that is being granted the specified authority.
     * It establishes the "role" side of the many-to-many relationship between roles
     * and authorities.
     */
    private Long roleId;

    /**
     * The unique identifier of the authority in this association.
     *
     * <p>This field references the authority that is being granted to the specified
     * role. It establishes the "authority" side of the many-to-many relationship
     * between roles and authorities.
     */
    private Long authorityId;

    /**
     * The timestamp when this role-authority association was created.
     *
     * <p>This field tracks when the permission was granted, which is useful for audit
     * trails and permission history tracking.
     */
    private Instant createdAt;

    /**
     * The timestamp when this role-authority association was last updated.
     *
     * <p>This field tracks when the association was last modified, which may be
     * relevant for audit and compliance purposes.
     */
    private Instant updatedAt;

    /**
     * Default constructor for serialisation frameworks.
     */
    public BizRoleAuthority() {
    }

    /**
     * Constructs a new BizRoleAuthority with all fields specified.
     *
     * @param roleId      the role identifier
     * @param authorityId the authority identifier
     * @param createdAt   the creation timestamp
     * @param updatedAt   the last update timestamp
     */
    public BizRoleAuthority(Long roleId, Long authorityId, Instant createdAt, Instant updatedAt) {
        this.roleId = roleId;
        this.authorityId = authorityId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * Gets the unique identifier of the role in this association.
     *
     * @return the role ID
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * Sets the unique identifier of the role in this association.
     *
     * @param roleId the role ID to set
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * Gets the unique identifier of the authority in this association.
     *
     * @return the authority ID
     */
    public Long getAuthorityId() {
        return authorityId;
    }

    /**
     * Sets the unique identifier of the authority in this association.
     *
     * @param authorityId the authority ID to set
     */
    public void setAuthorityId(Long authorityId) {
        this.authorityId = authorityId;
    }

    /**
     * Gets the timestamp when this role-authority association was created.
     *
     * @return the creation timestamp
     */
    public Instant getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the timestamp when this role-authority association was created.
     *
     * @param createdAt the creation timestamp to set
     */
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets the timestamp when this role-authority association was last updated.
     *
     * @return the last update timestamp
     */
    public Instant getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets the timestamp when this role-authority association was last updated.
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
        BizRoleAuthority that = (BizRoleAuthority) obj;
        return Objects.equals(roleId, that.roleId) &&
               Objects.equals(authorityId, that.authorityId) &&
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
        return Objects.hash(roleId, authorityId, createdAt, updatedAt);
    }

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return "BizRoleAuthority{" +
               "roleId=" + roleId +
               ", authorityId=" + authorityId +
               ", createdAt=" + createdAt +
               ", updatedAt=" + updatedAt +
               '}';
    }

    /**
     * Creates a new builder instance for constructing BizRoleAuthority instances.
     *
     * @return a new Builder instance
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing BizRoleAuthority instances using the builder pattern.
     *
     * <p>This builder provides a fluent interface for creating BizRoleAuthority instances
     * with optional parameters, improving code readability and maintainability.
     */
    public static class Builder {
        private Long roleId;
        private Long authorityId;
        private Instant createdAt;
        private Instant updatedAt;

        /**
         * Private constructor to enforce builder pattern usage.
         */
        private Builder() {
        }

        /**
         * Sets the unique identifier of the role in this association.
         *
         * @param roleId the role ID
         * @return this Builder instance for method chaining
         */
        public Builder roleId(Long roleId) {
            this.roleId = roleId;
            return this;
        }

        /**
         * Sets the unique identifier of the authority in this association.
         *
         * @param authorityId the authority ID
         * @return this Builder instance for method chaining
         */
        public Builder authorityId(Long authorityId) {
            this.authorityId = authorityId;
            return this;
        }

        /**
         * Sets the timestamp when this role-authority association was created.
         *
         * @param createdAt the creation timestamp
         * @return this Builder instance for method chaining
         */
        public Builder createdAt(Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        /**
         * Sets the timestamp when this role-authority association was last updated.
         *
         * @param updatedAt the last update timestamp
         * @return this Builder instance for method chaining
         */
        public Builder updatedAt(Instant updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        /**
         * Builds and returns a new BizRoleAuthority instance with the configured values.
         *
         * @return a new BizRoleAuthority instance
         */
        public BizRoleAuthority build() {
            return new BizRoleAuthority(roleId, authorityId, createdAt, updatedAt);
        }
    }
}
