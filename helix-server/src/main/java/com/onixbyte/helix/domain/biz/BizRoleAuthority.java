package com.onixbyte.helix.domain.biz;

import com.onixbyte.helix.domain.entity.RoleAuthority;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Business object representing the association between roles and authorities.
 * <p>
 * This class serves as a data transfer object for the RoleAuthority entity, providing a clean
 * interface for business logic operations while maintaining separation between the persistence
 * layer and business layer.
 *
 * @author zihluwang
 * @version 1.0
 * @since 1.0
 */
public class BizRoleAuthority {

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
    public BizRoleAuthority() {
    }

    /**
     * Constructor with all fields.
     *
     * @param roleId      the role identifier
     * @param authorityId the authority identifier
     * @param createdAt   the creation timestamp
     */
    public BizRoleAuthority(Long roleId, Long authorityId, LocalDateTime createdAt) {
        this.roleId = roleId;
        this.authorityId = authorityId;
        this.createdAt = createdAt;
    }

    /**
     * Creates a BizRoleAuthority from a RoleAuthority entity.
     *
     * @param entity the RoleAuthority entity
     * @return the BizRoleAuthority instance
     */
    public static BizRoleAuthority fromEntity(RoleAuthority entity) {
        if (entity == null) {
            return null;
        }
        return new BizRoleAuthority(
                entity.getRoleId(),
                entity.getAuthorityId(),
                entity.getCreatedAt()
        );
    }

    /**
     * Converts this BizRoleAuthority to a RoleAuthority entity.
     *
     * @return the RoleAuthority entity
     */
    public RoleAuthority asEntity() {
        return RoleAuthority.builder()
                .roleId(roleId)
                .authorityId(authorityId)
                .createdAt(createdAt)
                .build();
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
        BizRoleAuthority that = (BizRoleAuthority) o;
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
        return "BizRoleAuthority{" +
                "roleId=" + roleId +
                ", authorityId=" + authorityId +
                ", createdAt=" + createdAt +
                '}';
    }

    /**
     * Creates a new Builder instance for constructing BizRoleAuthority objects.
     *
     * @return a new BizRoleAuthorityBuilder instance
     */
    public static BizRoleAuthorityBuilder builder() {
        return new BizRoleAuthorityBuilder();
    }

    /**
     * Builder class for constructing BizRoleAuthority instances with a fluent API.
     */
    public static class BizRoleAuthorityBuilder {
        private Long roleId;
        private Long authorityId;
        private LocalDateTime createdAt;

        private BizRoleAuthorityBuilder() {
        }

        public BizRoleAuthorityBuilder roleId(Long roleId) {
            this.roleId = roleId;
            return this;
        }

        public BizRoleAuthorityBuilder authorityId(Long authorityId) {
            this.authorityId = authorityId;
            return this;
        }

        public BizRoleAuthorityBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        /**
         * Builds and returns a new BizRoleAuthority instance with the configured properties.
         *
         * @return a new BizRoleAuthority instance
         */
        public BizRoleAuthority build() {
            return new BizRoleAuthority(roleId, authorityId, createdAt);
        }
    }
}