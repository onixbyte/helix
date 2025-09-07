package com.onixbyte.helix.domain.entity;

import java.time.Instant;
import java.util.Objects;

public class RoleAuthority {

    private Long roleId;

    private Long permissionId;

    private Instant createdAt;

    public RoleAuthority() {
    }

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

    public static RoleAuthorityBuilder builder() {
        return new RoleAuthorityBuilder();
    }

    public static class RoleAuthorityBuilder {
        private Long roleId;
        private Long permissionId;
        private Instant createdAt;

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

        public RoleAuthority build() {
            return new RoleAuthority(roleId, permissionId, createdAt);
        }
    }
}
