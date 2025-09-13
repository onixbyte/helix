package com.onixbyte.helix.domain.entity;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents the association between roles and authorities in the access control system.
 * <p>
 * This entity implements the many-to-many relationship between roles and authorities, allowing
 * roles to be granted specific permissions. It forms a crucial part of the role-based access
 * control (RBAC) system by defining which authorities (permissions) are associated with each role.
 *
 * @author zihluwang
 * @version 1.0
 * @since 1.0
 */
public class RoleAuthority {

    /**
     * The identifier of the role in this association.
     * <p>
     * This field references the primary key of the Role entity that is being granted the
     * associated authority. Together with authorityId, it forms the composite primary key for this
     * association entity.
     */
    private Long roleId;

    /**
     * The identifier of the authority in this association.
     * <p>
     * This field references the primary key of the Authority entity that is being granted to the
     * associated role. Together with roleId, it forms the composite primary key for this
     * association entity.
     */
    private Long authorityId;

    /**
     * The timestamp when this role-authority association was created.
     * <p>
     * This field is automatically set when the association is first established and provides audit
     * information about when the authority was granted to the role. This is particularly useful for
     * compliance and security auditing purposes.
     */
    private LocalDateTime createdAt;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Long authorityId) {
        this.authorityId = authorityId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public RoleAuthority() {
    }

    public RoleAuthority(Long roleId, Long authorityId, LocalDateTime createdAt) {
        this.roleId = roleId;
        this.authorityId = authorityId;
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RoleAuthority that = (RoleAuthority) o;
        return Objects.equals(roleId, that.roleId) && Objects.equals(authorityId, that.authorityId) && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, authorityId, createdAt);
    }

    @Override
    public String toString() {
        return "RoleAuthority{" +
                "roleId=" + roleId +
                ", authorityId=" + authorityId +
                ", createdAt=" + createdAt +
                '}';
    }
}
