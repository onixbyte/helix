package com.onixbyte.helix.domain.entity;

import com.onixbyte.helix.constant.Status;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents a position entity within the organisational structure.
 * <p>
 * Positions define job roles or titles that can be assigned to users within the organisation. They
 * provide a way to categorise users based on their responsibilities and functions, complementing
 * the department-based organisational hierarchy. Positions can be used for reporting,
 * access control, and organisational management purposes.
 *
 * @author zihluwang
 * @version 1.0
 * @since 1.0
 */
public class Position {

    /**
     * The unique identifier for the position.
     * <p>
     * This serves as the primary key in the database and is used for all internal references to the
     * position entity.
     */
    private Long id;

    /**
     * The human-readable name of the position.
     * <p>
     * This field contains the job title or position name as it should appear in
     * organisational charts, user profiles, and administrative interfaces.
     */
    private String name;

    /**
     * The unique code identifier for the position.
     * <p>
     * This field contains a system-level identifier that uniquely identifies the position. It is
     * typically used for integration purposes and should follow a consistent naming convention.
     */
    private String code;

    /**
     * A detailed description of the position's responsibilities and requirements.
     * <p>
     * This field provides additional context about the role, including key responsibilities,
     * required skills, or other relevant information that helps define what this position entails.
     */
    private String description;

    /**
     * The sort order for displaying positions.
     * <p>
     * This field determines the order in which positions should be displayed in lists and
     * selection interfaces. Lower values indicate higher priority in sorting, which can reflect
     * organisational hierarchy or importance.
     */
    private Integer sort;

    /**
     * The current status of the position.
     * <p>
     * This field determines whether the position is active, inactive, or in any other state as
     * defined by the {@link Status} enumeration.
     */
    private Status status;

    /**
     * The timestamp when this position record was created.
     * <p>
     * This field is automatically set when the position entity is first persisted and provides
     * audit information about when the position was established.
     */
    private LocalDateTime createdAt;

    /**
     * The timestamp when this position record was last updated.
     * <p>
     * This field is automatically updated whenever any changes are made to the position entity and
     * provides audit information about the most recent modification.
     */
    private LocalDateTime updatedAt;

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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Position() {
    }

    public Position(Long id, String name, String code, String description, Integer sort, Status status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.description = description;
        this.sort = sort;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Objects.equals(id, position.id) && Objects.equals(name, position.name) && Objects.equals(code, position.code) && Objects.equals(description, position.description) && Objects.equals(sort, position.sort) && status == position.status && Objects.equals(createdAt, position.createdAt) && Objects.equals(updatedAt, position.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code, description, sort, status, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "Position{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", sort=" + sort +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    /**
     * Creates a new Builder instance for constructing Position objects.
     *
     * @return a new PositionBuilder instance
     */
    public static PositionBuilder builder() {
        return new PositionBuilder();
    }

    /**
     * Builder class for constructing Position instances with a fluent API.
     * <p>
     * This builder provides a convenient way to construct Position objects with optional parameters,
     * following the Builder pattern for improved readability and maintainability.
     */
    public static class PositionBuilder {
        private Long id;
        private String name;
        private String code;
        private String description;
        private Integer sort;
        private Status status;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        private PositionBuilder() {
        }

        public PositionBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public PositionBuilder name(String name) {
            this.name = name;
            return this;
        }

        public PositionBuilder code(String code) {
            this.code = code;
            return this;
        }

        public PositionBuilder description(String description) {
            this.description = description;
            return this;
        }

        public PositionBuilder sort(Integer sort) {
            this.sort = sort;
            return this;
        }

        public PositionBuilder status(Status status) {
            this.status = status;
            return this;
        }

        public PositionBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public PositionBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        /**
         * Builds and returns a new Position instance with the configured properties.
         *
         * @return a new Position instance
         */
        public Position build() {
            return new Position(id, name, code, description, sort, status, createdAt, updatedAt);
        }
    }
}
