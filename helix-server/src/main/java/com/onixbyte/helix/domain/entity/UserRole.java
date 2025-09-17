package com.onixbyte.helix.domain.entity;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents the association between users and roles in the access control system.
 * <p>
 * This entity implements the many-to-many relationship between users and roles, allowing users to
 * be assigned multiple roles and roles to be assigned to multiple users. It forms a fundamental
 * part of the role-based access control (RBAC) system by defining which roles are assigned to
 * each user, thereby determining their permissions and access levels within the system.
 *
 * @author zihluwang
 * @version 1.0
 * @since 1.0
 */
public class UserRole {

    /**
     * The identifier of the role in this association.
     * <p>
     * This field references the primary key of the Role entity that is being assigned to the
     * associated user. Together with userId, it forms the composite primary key for this
     * association entity.
     */
    private Long roleId;

    /**
     * The identifier of the user in this association.
     * <p>
     * This field references the primary key of the User entity that is being assigned the
     * associated role. Together with roleId, it forms the composite primary key for this
     * association entity.
     */
    private Long userId;

    /**
     * The timestamp when this user-role assignment was created.
     * <p>
     * This field is automatically set when the role assignment is first established and provides
     * audit information about when the role was assigned to the user. This is essential
     * for compliance, security auditing, and tracking changes in user permissions over time.
     */
    private LocalDateTime createdAt;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public UserRole() {
    }

    public UserRole(Long roleId, Long userId, LocalDateTime createdAt) {
        this.roleId = roleId;
        this.userId = userId;
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserRole userRole = (UserRole) o;
        return Objects.equals(roleId, userRole.roleId) && Objects.equals(userId, userRole.userId) && Objects.equals(createdAt, userRole.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, userId, createdAt);
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "roleId=" + roleId +
                ", userId=" + userId +
                ", createdAt=" + createdAt +
                '}';
    }

    /**
     * Creates a new Builder instance for constructing UserRole objects.
     *
     * @return a new UserRoleBuilder instance
     */
    public static UserRoleBuilder builder() {
        return new UserRoleBuilder();
    }

    /**
     * Builder class for constructing UserRole instances with a fluent API.
     * <p>
     * This builder provides a convenient way to construct UserRole objects with optional parameters,
     * following the Builder pattern for improved readability and maintainability.
     */
    public static class UserRoleBuilder {
        private Long roleId;
        private Long userId;
        private LocalDateTime createdAt;

        private UserRoleBuilder() {
        }

        public UserRoleBuilder roleId(Long roleId) {
            this.roleId = roleId;
            return this;
        }

        public UserRoleBuilder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public UserRoleBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        /**
         * Builds and returns a new UserRole instance with the configured properties.
         *
         * @return a new UserRole instance
         */
        public UserRole build() {
            return new UserRole(roleId, userId, createdAt);
        }
    }
}
