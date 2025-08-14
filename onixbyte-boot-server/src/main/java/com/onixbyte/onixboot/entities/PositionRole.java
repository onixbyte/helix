package com.onixbyte.onixboot.entities;

import java.time.Instant;
import java.util.Objects;

public class PositionRole {

    private Long positionId;

    private Long roleId;

    private Instant createdAt;

    public PositionRole() {
    }

    public PositionRole(Long positionId, Long roleId, Instant createdAt) {
        this.positionId = positionId;
        this.roleId = roleId;
        this.createdAt = createdAt;
    }

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
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
        PositionRole that = (PositionRole) o;
        return Objects.equals(positionId, that.positionId) &&
                Objects.equals(roleId, that.roleId) &&
                Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(positionId, roleId, createdAt);
    }

    @Override
    public String toString() {
        return "PositionRole{" +
                "positionId=" + positionId +
                ", roleId=" + roleId +
                ", createdAt=" + createdAt +
                '}';
    }
}
