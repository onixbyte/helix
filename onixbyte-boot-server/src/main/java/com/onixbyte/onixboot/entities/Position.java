package com.onixbyte.onixboot.entities;

import com.onixbyte.onixboot.enums.NormalStatus;

import java.time.Instant;
import java.util.Objects;

public class Position {

    private Long id;

    private String name;

    private String code;

    private String description;

    private Integer sortOrder;

    private NormalStatus status;

    private Instant createdAt;

    private Instant updatedAt;

    public Position() {
    }

    public Position(Long id, String name, String code, String description, Integer sortOrder, NormalStatus status, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.description = description;
        this.sortOrder = sortOrder;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public NormalStatus getStatus() {
        return status;
    }

    public void setStatus(NormalStatus status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Objects.equals(id, position.id) &&
                Objects.equals(name, position.name) &&
                Objects.equals(code, position.code) &&
                Objects.equals(description, position.description) &&
                Objects.equals(sortOrder, position.sortOrder) &&
                status == position.status &&
                Objects.equals(createdAt, position.createdAt) &&
                Objects.equals(updatedAt, position.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code, description, sortOrder, status, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "Position{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", sortOrder=" + sortOrder +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
