package com.onixbyte.helix.domain.entity;

import com.onixbyte.helix.domain.entity.embeddable.RoleAuthorityId;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents the association entity between a Role and an Authority in a many-to-many relationship.
 * <p>
 * This entity is mapped to the 'role_authorities' table and uses a composite primary key
 * {@code @EmbeddedId} defined in the RoleAuthorityId class. It also includes the 'createdAt'
 * auditing field.
 */
@Entity
@Table(name = "role_authorities")
public class RoleAuthority {

    /**
     * The composite primary key of the association, mapped to the 'role_id' and 'authority_id' columns.
     * <p>
     * This field embeds the RoleAuthorityId object which holds the foreign keys to the Role and Authority tables.
     */
    @EmbeddedId
    private RoleAuthorityId id;

    /**
     * The timestamp when this role-authority association was created.
     * <p>
     * This field is automatically set upon creation in the database. {@code @Column} maps the field
     * to the 'created_at' column.
     */
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public Long getRoleId() {
        return this.id != null ? this.id.getRoleId() : null;
    }

    public Long getAuthorityId() {
        return this.id != null ? this.id.getAuthorityId() : null;
    }

    public RoleAuthorityId getId() {
        return id;
    }

    public void setId(RoleAuthorityId id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public RoleAuthority() {
        // Initialise the ID for safety when created via JPA
        this.id = new RoleAuthorityId();
    }

    public RoleAuthority(Long roleId, Long authorityId, LocalDateTime createdAt) {
        this.id = new RoleAuthorityId(roleId, authorityId);
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleAuthority that = (RoleAuthority) o;
        // Only check the primary key for entity equality
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        // Hash code based on the primary key
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "RoleAuthority{" +
                "roleId=" + (id != null ? id.getRoleId() : "null") +
                ", authorityId=" + (id != null ? id.getAuthorityId() : "null") +
                ", createdAt=" + createdAt +
                '}';
    }

    public static RoleAuthorityBuilder builder() {
        return new RoleAuthorityBuilder();
    }

    public static class RoleAuthorityBuilder {
        private Long roleId;
        private Long authorityId;
        private LocalDateTime createdAt;

        private RoleAuthorityBuilder() {
        }

        public RoleAuthorityBuilder roleId(Long roleId) {
            this.roleId = roleId;
            return this;
        }

        public RoleAuthorityBuilder authorityId(Long authorityId) {
            this.authorityId = authorityId;
            return this;
        }

        public RoleAuthorityBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public RoleAuthority build() {
            return new RoleAuthority(roleId, authorityId, createdAt);
        }
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
