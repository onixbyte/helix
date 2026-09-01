package com.onixbyte.helix.domain.entity.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * Represents the composite primary key for the UserRole association entity.
 * <p>
 * This class combines the userId and roleId to uniquely identify a user's role assignment.
 */
@Embeddable
public class UserRoleId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * The identifier of the user in this association, mapping to the 'user_id' column.
     */
    @Column(nullable = false)
    private Long userId;

    /**
     * The identifier of the role in this association, mapping to the 'role_id' column.
     */
    @Column(nullable = false)
    private Long roleId;

    // --- Constructors ---
    public UserRoleId() {
    }

    public UserRoleId(Long userId, Long roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    // --- Getters and Setters ---
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    // --- equals and hashCode (REQUIRED for composite keys) ---
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRoleId that = (UserRoleId) o;
        return Objects.equals(userId, that.userId) && Objects.equals(roleId, that.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, roleId);
    }
}
