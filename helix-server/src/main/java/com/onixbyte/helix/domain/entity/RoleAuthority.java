package com.onixbyte.helix.domain.entity;

import java.time.Instant;
import java.util.Objects;

/**
 * Entity representing the many-to-many relationship between roles and authorities.
 * <p>
 * This entity serves as a junction table in the Role-Based Access Control (RBAC) system, linking
 * {@link Role} entities with {@link Authority} entities to define which permissions are granted
 * to each role.
 * <p>
 * Each record in this entity represents a single permission assignment, indicating that users
 * assigned to a specific role are granted the associated authority. This design allows for flexible
 * and granular permission management, where roles can be composed of multiple authorities, and
 * authorities can be shared across multiple roles.
 * <p>
 * The entity maintains a creation timestamp to track when the permission assignment
 * was established, which can be useful for auditing and compliance purposes.
 *
 * @author zihluwang
 * @see Role
 * @see Authority
 * @see User
 * @since 1.0.0
 */
public class RoleAuthority {

    /**
     * The identifier of the role in this role-authority relationship.
     * <p>
     * This field references the {@link Role#getId()} and establishes which role is being granted
     * the associated authority.
     */
    private Long roleId;

    /**
     * The identifier of the authority in this role-authority relationship.
     * <p>
     * This field references the {@link Authority#getId()} and establishes which specific permission
     * or authority is being granted to the role.
     * <p>
     * Note: The field name uses 'permissionId' for historical reasons, but it actually references
     * an Authority entity.
     */
    private Long permissionId;

    /**
     * The timestamp when this role-authority relationship was created.
     * <p>
     * This field is useful for auditing purposes and tracking when specific permissions were
     * granted to roles.
     */
    private Instant createdAt;

    /**
     * Default constructor for JPA and serialisation frameworks.
     */
    public RoleAuthority() {
    }

    /**
     * Constructs a new RoleAuthority with all fields specified.
     *
     * @param roleId       the identifier of the role
     * @param permissionId the identifier of the authority (permission)
     * @param createdAt    the creation timestamp
     */
    public RoleAuthority(Long roleId, Long permissionId, Instant createdAt) {
        this.roleId = roleId;
        this.permissionId = permissionId;
        this.createdAt = createdAt;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RoleAuthority that = (RoleAuthority) o;
        return Objects.equals(roleId, that.roleId) &&
                Objects.equals(permissionId, that.permissionId) &&
                Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, permissionId, createdAt);
    }

    @Override
    public String toString() {
        return "RolePermission{" +
                "roleId=" + roleId +
                ", permissionId=" + permissionId +
                ", createdAt=" + createdAt +
                '}';
    }

    /**
     * Creates a new builder instance for constructing RoleAuthority objects.
     *
     * @return a new {@link RoleAuthorityBuilder} instance
     */
    public static RoleAuthorityBuilder builder() {
        return new RoleAuthorityBuilder();
    }

    /**
     * Builder class for constructing {@link RoleAuthority} instances.
     * <p>
     * This builder provides a fluent interface for setting role-authority relationship properties
     * and ensures consistent object construction.
     */
    public static class RoleAuthorityBuilder {
        private Long roleId;
        private Long permissionId;
        private Instant createdAt;

        /**
         * Private constructor to enforce builder pattern usage.
         */
        private RoleAuthorityBuilder() {
        }

        public RoleAuthorityBuilder roleId(Long roleId) {
            this.roleId = roleId;
            return this;
        }

        public RoleAuthorityBuilder permissionId(Long permissionId) {
            this.permissionId = permissionId;
            return this;
        }

        public RoleAuthorityBuilder createdAt(Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        /**
         * Builds and returns a new RoleAuthority instance with the configured properties.
         *
         * @return a new {@link RoleAuthority} instance
         */
        public RoleAuthority build() {
            return new RoleAuthority(roleId, permissionId, createdAt);
        }
    }
}
