package com.onixbyte.helix.domain.entity;

import com.onixbyte.helix.constant.NormalStatus;

import java.time.Instant;
import java.util.Objects;

/**
 * Entity representing job positions within the Helix application.
 * <p>
 * A position defines a specific role or job function within an organisation, such as
 * "{@code Software Engineer}", "{@code Project Manager}", or "{@code Sales Representative}".
 * Positions are used in conjunction with departments to define the organisational structure and can
 * be assigned to users to establish their roles and responsibilities.
 * <p>
 * Each position has a unique code for system identification, a descriptive name for
 * display purposes, and an optional description providing additional details about the role's
 * responsibilities and requirements.
 * <p>
 * Positions support ordering through the sort order field and can be activated or deactivated using
 * the status field to manage organisational changes.
 *
 * @author zihluwang
 * @see User
 * @see Department
 * @see NormalStatus
 * @since 1.0.0
 */
public class Position {

    /**
     * The unique identifier for this position.
     */
    private Long id;

    /**
     * The display name of this position.
     * <p>
     * This should be a human-readable name that clearly describes the job role or function, such as
     * "{@code Senior Software Engineer}" or "{@code Marketing Manager}".
     */
    private String name;

    /**
     * The unique code identifier for this position.
     * <p>
     * This code is used for system identification and should be unique across all positions.
     * It's typically used in integrations and internal system references.
     */
    private String code;

    /**
     * A detailed description of this position.
     * <p>
     * This field can contain information about the role's responsibilities, requirements,
     * qualifications, and other relevant details that help define the position's scope
     * and expectations.
     */
    private String description;

    /**
     * The sort order for this position.
     * <p>
     * Used to maintain consistent ordering when displaying positions in lists or
     * hierarchical views.
     */
    private Integer sortOrder;

    /**
     * The current status of this position.
     * <p>
     * Determines whether this position is active and available for assignment within
     * the organisation.
     */
    private NormalStatus status;

    /**
     * The timestamp when this position was created.
     */
    private Instant createdAt;

    /**
     * The timestamp when this position was last updated.
     */
    private Instant updatedAt;

    /**
     * Default constructor for JPA and serialisation frameworks.
     */
    public Position() {
    }

    /**
     * Constructs a new Position with all fields specified.
     *
     * @param id          the unique identifier
     * @param name        the position name
     * @param code        the unique position code
     * @param description the detailed description of the position
     * @param sortOrder   the sort order for display purposes
     * @param status      the current status
     * @param createdAt   the creation timestamp
     * @param updatedAt   the last update timestamp
     */
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

    /**
     * Creates a new builder instance for constructing Position objects.
     *
     * @return a new {@link PositionBuilder} instance
     */
    public static PositionBuilder builder() {
        return new PositionBuilder();
    }

    /**
     * Builder class for constructing {@link Position} instances.
     * <p>
     * This builder provides a fluent interface for setting position properties and ensures
     * consistent object construction.
     */
    public static class PositionBuilder {
        private Long id;
        private String name;
        private String code;
        private String description;
        private Integer sortOrder;
        private NormalStatus status;
        private Instant createdAt;
        private Instant updatedAt;

        /**
         * Private constructor to enforce builder pattern usage.
         */
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

        public PositionBuilder sortOrder(Integer sortOrder) {
            this.sortOrder = sortOrder;
            return this;
        }

        public PositionBuilder status(NormalStatus status) {
            this.status = status;
            return this;
        }

        public PositionBuilder createdAt(Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public PositionBuilder updatedAt(Instant updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        /**
         * Builds and returns a new Position instance with the configured properties.
         *
         * @return a new {@link Position} instance
         */
        public Position build() {
            return new Position(id, name, code, description, sortOrder, status, createdAt, updatedAt);
        }
    }
}
