package com.onixbyte.helix.domain.biz;

import com.onixbyte.helix.domain.entity.UserRole;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Business object representing the association between users and roles in the access
 * control system.
 * <p>
 * This class serves as a data transfer object for user-role association operations, providing a
 * clean interface for business logic while maintaining separation from the underlying
 * entity representation.
 *
 * @author zihluwang
 * @version 1.0
 * @since 1.0
 */
public class BizUserRole {

    /**
     * The identifier of the role in this association.
     */
    private Long roleId;

    /**
     * The identifier of the user in this association.
     */
    private Long userId;

    /**
     * The timestamp when this user-role assignment was created.
     */
    private LocalDateTime createdAt;

    /**
     * Default constructor.
     */
    public BizUserRole() {
    }

    /**
     * Constructs a new BizUserRole with all fields specified.
     *
     * @param roleId    the identifier of the role
     * @param userId    the identifier of the user
     * @param createdAt the creation timestamp
     */
    public BizUserRole(Long roleId, Long userId, LocalDateTime createdAt) {
        this.roleId = roleId;
        this.userId = userId;
        this.createdAt = createdAt;
    }

    /**
     * Creates a BizUserRole from a UserRole entity.
     *
     * @param userRole the UserRole entity
     * @return a new BizUserRole instance
     */
    public static BizUserRole fromEntity(UserRole userRole) {
        if (userRole == null) {
            return null;
        }
        return new BizUserRole(
                userRole.getRoleId(),
                userRole.getUserId(),
                userRole.getCreatedAt()
        );
    }

    /**
     * Converts this BizUserRole to a UserRole entity.
     *
     * @return a new UserRole entity
     */
    public UserRole asEntity() {
        return UserRole.builder()
                .roleId(roleId)
                .userId(userId)
                .createdAt(createdAt)
                .build();
    }

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BizUserRole that = (BizUserRole) o;
        return Objects.equals(roleId, that.roleId) && Objects.equals(userId, that.userId) &&
                Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, userId, createdAt);
    }

    @Override
    public String toString() {
        return "BizUserRole{" +
                "roleId=" + roleId +
                ", userId=" + userId +
                ", createdAt=" + createdAt +
                '}';
    }

    /**
     * Creates a new Builder instance for constructing BizUserRole objects.
     *
     * @return a new BizUserRoleBuilder instance
     */
    public static BizUserRoleBuilder builder() {
        return new BizUserRoleBuilder();
    }

    /**
     * Builder class for constructing BizUserRole instances with a fluent API.
     */
    public static class BizUserRoleBuilder {
        private Long roleId;
        private Long userId;
        private LocalDateTime createdAt;

        private BizUserRoleBuilder() {
        }

        public BizUserRoleBuilder roleId(Long roleId) {
            this.roleId = roleId;
            return this;
        }

        public BizUserRoleBuilder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public BizUserRoleBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        /**
         * Builds and returns a new BizUserRole instance with the configured properties.
         *
         * @return a new BizUserRole instance
         */
        public BizUserRole build() {
            return new BizUserRole(roleId, userId, createdAt);
        }
    }
}