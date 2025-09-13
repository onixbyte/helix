package com.onixbyte.helix.domain.view;

import java.time.Instant;
import java.util.Objects;

/**
 * View object representing role-authority associations for presentation layer operations.
 * <p>
 * This view object encapsulates role-authority relationship data optimised for frontend display and
 * user interface operations. It provides a clean, presentation-focused interface for role-based
 * access control (RBAC) information whilst maintaining appropriate data visibility for
 * client-side operations.
 * <p>
 * The RoleAuthorityView contains information suitable for display in user interfaces,
 * administrative panels, and API responses. It focuses on providing the essential data required for
 * permission management interfaces whilst excluding sensitive or implementation-specific details.
 * <p>
 * This object is designed to be serialised for API responses, used in frontend applications, and
 * serves as the primary data structure for permission-related user interface components.
 *
 * @author zihluwang
 * @see com.onixbyte.helix.domain.entity.RoleAuthority
 * @see com.onixbyte.helix.domain.biz.BizRoleAuthority
 * @since 1.0.0
 */
public class RoleAuthorityView {

    /**
     * The unique identifier of the role in this association.
     * <p>
     * This field identifies the role that has been granted the specified authority, enabling
     * frontend applications to display and manage role-authority relationships.
     */
    private Long roleId;

    /**
     * The unique identifier of the authority in this association.
     * <p>
     * This field identifies the authority that has been granted to the specified role, enabling
     * permission management interfaces to display current assignments.
     */
    private Long authorityId;

    /**
     * The timestamp when this role-authority association was created.
     * <p>
     * This field provides audit information for when permissions were granted, useful for
     * compliance and administrative tracking.
     */
    private Instant createdAt;

    /**
     * The timestamp when this role-authority association was last updated.
     * <p>
     * This field provides information about when the association was last modified, useful for
     * audit trails and change tracking.
     */
    private Instant updatedAt;

    /**
     * Default constructor for serialisation frameworks.
     */
    public RoleAuthorityView() {
    }

    /**
     * Constructs a new RoleAuthorityView with all fields specified.
     *
     * @param roleId      the role identifier
     * @param authorityId the authority identifier
     * @param createdAt   the creation timestamp
     * @param updatedAt   the last update timestamp
     */
    public RoleAuthorityView(Long roleId, Long authorityId, Instant createdAt, Instant updatedAt) {
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
        RoleAuthorityView that = (RoleAuthorityView) obj;
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
        return "RoleAuthorityView{" +
                "roleId=" + roleId +
                ", authorityId=" + authorityId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    /**
     * Creates a new builder instance for constructing RoleAuthorityView instances.
     *
     * @return a new RoleAuthorityViewBuilder instance
     */
    public static RoleAuthorityViewBuilder builder() {
        return new RoleAuthorityViewBuilder();
    }

    /**
     * RoleAuthorityViewBuilder class for constructing RoleAuthorityView instances using the
     * builder pattern.
     * <p>
     * This builder provides a fluent interface for creating RoleAuthorityView instances with
     * optional parameters, improving code readability and maintainability.
     */
    public static class RoleAuthorityViewBuilder {
        private Long roleId;
        private Long authorityId;
        private Instant createdAt;
        private Instant updatedAt;

        /**
         * Private constructor to enforce builder pattern usage.
         */
        private RoleAuthorityViewBuilder() {
        }

        /**
         * Sets the unique identifier of the role in this association.
         *
         * @param roleId the role ID
         * @return this RoleAuthorityViewBuilder instance for method chaining
         */
        public RoleAuthorityViewBuilder roleId(Long roleId) {
            this.roleId = roleId;
            return this;
        }

        /**
         * Sets the unique identifier of the authority in this association.
         *
         * @param authorityId the authority ID
         * @return this RoleAuthorityViewBuilder instance for method chaining
         */
        public RoleAuthorityViewBuilder authorityId(Long authorityId) {
            this.authorityId = authorityId;
            return this;
        }

        /**
         * Sets the timestamp when this role-authority association was created.
         *
         * @param createdAt the creation timestamp
         * @return this RoleAuthorityViewBuilder instance for method chaining
         */
        public RoleAuthorityViewBuilder createdAt(Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        /**
         * Sets the timestamp when this role-authority association was last updated.
         *
         * @param updatedAt the last update timestamp
         * @return this RoleAuthorityViewBuilder instance for method chaining
         */
        public RoleAuthorityViewBuilder updatedAt(Instant updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        /**
         * Builds and returns a new RoleAuthorityView instance with the configured values.
         *
         * @return a new RoleAuthorityView instance
         */
        public RoleAuthorityView build() {
            return new RoleAuthorityView(roleId, authorityId, createdAt, updatedAt);
        }
    }
}
