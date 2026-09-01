package com.onixbyte.helix.domain.entity.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * Represents the composite primary key for the RoleAuthority association entity.
 */
@Embeddable
public class RoleAuthorityId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * The identifier of the role, mapping to the 'role_id' column.
     * <p>
     * This field should match the corresponding field name in the RoleAuthority entity
     * if the naming is non-standard, but typically matches the column name in the database.
     */
    @Column(nullable = false)
    private Long roleId;

    /**
     * The identifier of the authority, mapping to the 'authority_id' column.
     */
    @Column(nullable = false)
    private Long authorityId;

    public RoleAuthorityId() {
    }

    public RoleAuthorityId(Long roleId, Long authorityId) {
        this.roleId = roleId;
        this.authorityId = authorityId;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleAuthorityId that = (RoleAuthorityId) o;
        return Objects.equals(roleId, that.roleId) && Objects.equals(authorityId, that.authorityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, authorityId);
    }
}
