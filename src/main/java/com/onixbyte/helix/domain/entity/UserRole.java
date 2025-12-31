package com.onixbyte.helix.domain.entity;

import com.onixbyte.helix.domain.entity.embeddable.UserRoleId;
import jakarta.persistence.*;
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
@Entity
@Table(name = "user_roles")
public class UserRole {

    /**
     * The composite primary key of the association, mapped to the 'user_id' and 'role_id' columns.
     * <p>
     * This field embeds the UserRoleId object which holds the foreign keys to the User and Role tables.
     */
    @EmbeddedId
    private UserRoleId id;

    /**
     * The timestamp when this user-role assignment was created.
     * <p>
     * This field is automatically set upon creation in the database.
     */
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * Gets the identifier of the role from the composite primary key.
     *
     * @return the role's unique identifier
     */
    public Long getRoleId() {
        return this.id != null ? this.id.getRoleId() : null;
    }

    /**
     * Sets the identifier of the role within the composite primary key.
     *
     * @param roleId the role's unique identifier
     */
    public void setRoleId(Long roleId) {
        if (this.id == null) this.id = new UserRoleId();
        this.id.setRoleId(roleId);
    }

    /**
     * Gets the identifier of the user from the composite primary key.
     *
     * @return the user's unique identifier
     */
    public Long getUserId() {
        return this.id != null ? this.id.getUserId() : null;
    }

    /**
     * Sets the identifier of the user within the composite primary key.
     *
     * @param userId the user's unique identifier
     */
    public void setUserId(Long userId) {
        if (this.id == null) this.id = new UserRoleId();
        this.id.setUserId(userId);
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public UserRole() {
        this.id = new UserRoleId();
    }

    public UserRole(Long roleId, Long userId, LocalDateTime createdAt) {
        this.id = new UserRoleId(userId, roleId);
        this.createdAt = createdAt;
    }

    // --- Overrides (Simplified to use the Id object for entity equality) ---

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRole userRole = (UserRole) o;
        return Objects.equals(id, userRole.id); // Entity equality based on primary key
    }

    @Override
    public int hashCode() {
        return Objects.hash(id); // Hash code based on primary key
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "roleId=" + getRoleId() +
                ", userId=" + getUserId() +
                ", createdAt=" + createdAt +
                '}';
    }

    // --- Builder Class (Adjusted to build the Id object) ---

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

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}