package com.onixbyte.helix.domain.view;

import com.onixbyte.helix.domain.entity.RoleAuthority;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * View object for RoleAuthority entity.
 * <p>
 * This class represents a view of the RoleAuthority entity, providing a data transfer object
 * for role-authority association information in the access control system.
 *
 * @author zihluwang
 * @version 1.0
 * @since 1.0
 */
public class RoleAuthorityView {

    /**
     * The identifier of the role in this association.
     */
    private Long roleId;

    /**
     * The identifier of the authority in this association.
     */
    private Long authorityId;

    /**
     * The timestamp when this role-authority association was created.
     */
    private LocalDateTime createdAt;

    /**
     * Default constructor.
     */
    public RoleAuthorityView() {
    }

    /**
     * Constructor with all fields.
     */
    public RoleAuthorityView(Long roleId, Long authorityId, LocalDateTime createdAt) {
        this.roleId = roleId;
        this.authorityId = authorityId;
        this.createdAt = createdAt;
    }

    /**
     * Creates a RoleAuthorityView from a RoleAuthority entity.
     *
     * @param roleAuthority the RoleAuthority entity
     * @return the RoleAuthorityView object
     */
    public static RoleAuthorityView fromEntity(RoleAuthority roleAuthority) {
        if (roleAuthority == null) {
            return null;
        }
        return new RoleAuthorityView(
                roleAuthority.getRoleId(),
                roleAuthority.getAuthorityId(),
                roleAuthority.getCreatedAt()
        );
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleAuthorityView that = (RoleAuthorityView) o;
        return Objects.equals(roleId, that.roleId) &&
                Objects.equals(authorityId, that.authorityId) &&
                Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, authorityId, createdAt);
    }

    @Override
    public String toString() {
        return "RoleAuthorityView{" +
                "roleId=" + roleId +
                ", authorityId=" + authorityId +
                ", createdAt=" + createdAt +
                '}';
    }

    /**
     * Creates a new builder instance.
     *
     * @return a new RoleAuthorityViewBuilder
     */
    public static RoleAuthorityViewBuilder builder() {
        return new RoleAuthorityViewBuilder();
    }

    /**
     * Builder class for RoleAuthorityView.
     */
    public static class RoleAuthorityViewBuilder {
        private Long roleId;
        private Long authorityId;
        private LocalDateTime createdAt;

        private RoleAuthorityViewBuilder() {
        }

        public RoleAuthorityViewBuilder roleId(Long roleId) {
            this.roleId = roleId;
            return this;
        }

        public RoleAuthorityViewBuilder authorityId(Long authorityId) {
            this.authorityId = authorityId;
            return this;
        }

        public RoleAuthorityViewBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public RoleAuthorityView build() {
            return new RoleAuthorityView(roleId, authorityId, createdAt);
        }
    }
}