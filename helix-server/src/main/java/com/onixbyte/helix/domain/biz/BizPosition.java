package com.onixbyte.helix.domain.biz;

import com.onixbyte.helix.constant.Status;
import com.onixbyte.helix.domain.entity.Position;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Business object representing a position within the organisational structure.
 * <p>
 * This class serves as a data transfer object for position-related operations, providing a clean
 * interface for business logic while maintaining separation from the underlying
 * entity representation.
 *
 * @author zihluwang
 * @version 1.0
 * @since 1.0
 */
public class BizPosition {

    /**
     * The unique identifier for the position.
     */
    private Long id;

    /**
     * The human-readable name of the position.
     */
    private String name;

    /**
     * The unique code identifier for the position.
     */
    private String code;

    /**
     * A detailed description of the position's responsibilities and requirements.
     */
    private String description;

    /**
     * The sort order for displaying positions.
     */
    private Integer sort;

    /**
     * The current status of the position.
     */
    private Status status;

    /**
     * The timestamp when this position record was created.
     */
    private LocalDateTime createdAt;

    /**
     * The timestamp when this position record was last updated.
     */
    private LocalDateTime updatedAt;

    /**
     * Default constructor.
     */
    public BizPosition() {
    }

    /**
     * Constructs a new BizPosition with all fields specified.
     *
     * @param id          the unique identifier
     * @param name        the position name
     * @param code        the unique position code
     * @param description the detailed description of the position
     * @param sort        the sort order for display purposes
     * @param status      the current status
     * @param createdAt   the creation timestamp
     * @param updatedAt   the last update timestamp
     */
    public BizPosition(Long id, String name, String code, String description, Integer sort, Status status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.description = description;
        this.sort = sort;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * Creates a BizPosition from a Position entity.
     *
     * @param position the Position entity
     * @return a new BizPosition instance
     */
    public static BizPosition fromEntity(Position position) {
        if (position == null) {
            return null;
        }
        return new BizPosition(
                position.getId(),
                position.getName(),
                position.getCode(),
                position.getDescription(),
                position.getSort(),
                position.getStatus(),
                position.getCreatedAt(),
                position.getUpdatedAt()
        );
    }

    /**
     * Converts this BizPosition to a Position entity.
     *
     * @return a new Position entity
     */
    public Position asEntity() {
        return Position.builder()
                .id(id)
                .name(name)
                .code(code)
                .description(description)
                .sort(sort)
                .status(status)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BizPosition that = (BizPosition) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) &&
                Objects.equals(code, that.code) && Objects.equals(description, that.description) &&
                Objects.equals(sort, that.sort) && status == that.status &&
                Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code, description, sort, status, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "BizPosition{" +
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
     * Creates a new Builder instance for constructing BizPosition objects.
     *
     * @return a new BizPositionBuilder instance
     */
    public static BizPositionBuilder builder() {
        return new BizPositionBuilder();
    }

    /**
     * Builder class for constructing BizPosition instances with a fluent API.
     */
    public static class BizPositionBuilder {
        private Long id;
        private String name;
        private String code;
        private String description;
        private Integer sort;
        private Status status;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        private BizPositionBuilder() {
        }

        public BizPositionBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public BizPositionBuilder name(String name) {
            this.name = name;
            return this;
        }

        public BizPositionBuilder code(String code) {
            this.code = code;
            return this;
        }

        public BizPositionBuilder description(String description) {
            this.description = description;
            return this;
        }

        public BizPositionBuilder sort(Integer sort) {
            this.sort = sort;
            return this;
        }

        public BizPositionBuilder status(Status status) {
            this.status = status;
            return this;
        }

        public BizPositionBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public BizPositionBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        /**
         * Builds and returns a new BizPosition instance with the configured properties.
         *
         * @return a new BizPosition instance
         */
        public BizPosition build() {
            return new BizPosition(id, name, code, description, sort, status, createdAt, updatedAt);
        }
    }
}