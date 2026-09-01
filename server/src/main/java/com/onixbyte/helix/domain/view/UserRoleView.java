package com.onixbyte.helix.domain.view;

import com.onixbyte.helix.domain.entity.UserRole;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * View object for UserRole entity.
 * <p>
 * This class represents a view of the UserRole entity, providing a data transfer object
 * for user-role association information in the access control system.
 *
 * @author zihluwang
 * @version 1.0
 * @since 1.0
 */
public class UserRoleView {

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
    public UserRoleView() {
    }

    /**
     * Constructor with all fields.
     */
    public UserRoleView(Long roleId, Long userId, LocalDateTime createdAt) {
        this.roleId = roleId;
        this.userId = userId;
        this.createdAt = createdAt;
    }

    /**
     * Creates a UserRoleView from a UserRole entity.
     *
     * @param userRole the UserRole entity
     * @return the UserRoleView object
     */
    public static UserRoleView fromEntity(UserRole userRole) {
        if (userRole == null) {
            return null;
        }
        return new UserRoleView(
                userRole.getRoleId(),
                userRole.getUserId(),
                userRole.getCreatedAt()
        );
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRoleView that = (UserRoleView) o;
        return Objects.equals(roleId, that.roleId) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, userId, createdAt);
    }

    @Override
    public String toString() {
        return "UserRoleView{" +
                "roleId=" + roleId +
                ", userId=" + userId +
                ", createdAt=" + createdAt +
                '}';
    }

    /**
     * Creates a new builder instance.
     *
     * @return a new UserRoleViewBuilder
     */
    public static UserRoleViewBuilder builder() {
        return new UserRoleViewBuilder();
    }

    /**
     * Builder class for UserRoleView.
     */
    public static class UserRoleViewBuilder {
        private Long roleId;
        private Long userId;
        private LocalDateTime createdAt;

        private UserRoleViewBuilder() {
        }

        public UserRoleViewBuilder roleId(Long roleId) {
            this.roleId = roleId;
            return this;
        }

        public UserRoleViewBuilder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public UserRoleViewBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public UserRoleView build() {
            return new UserRoleView(roleId, userId, createdAt);
        }
    }
}