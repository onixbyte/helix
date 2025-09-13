package com.onixbyte.helix.domain.view;

import com.onixbyte.helix.constant.Status;
import com.onixbyte.helix.domain.entity.Position;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * View object for Position entity.
 * <p>
 * This class represents a view of the Position entity, providing a data transfer object
 * for position information in the organisational structure.
 *
 * @author zihluwang
 * @version 1.0
 * @since 1.0
 */
public class PositionView {

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
    public PositionView() {
    }

    /**
     * Constructor with all fields.
     */
    public PositionView(Long id, String name, String code, String description, Integer sort,
                       Status status, LocalDateTime createdAt, LocalDateTime updatedAt) {
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
     * Creates a PositionView from a Position entity.
     *
     * @param position the Position entity
     * @return the PositionView object
     */
    public static PositionView fromEntity(Position position) {
        if (position == null) {
            return null;
        }
        return new PositionView(
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PositionView that = (PositionView) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(code, that.code) &&
                Objects.equals(description, that.description) &&
                Objects.equals(sort, that.sort) &&
                status == that.status &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code, description, sort, status, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "PositionView{" +
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
     * Creates a new builder instance.
     *
     * @return a new PositionViewBuilder
     */
    public static PositionViewBuilder builder() {
        return new PositionViewBuilder();
    }

    /**
     * Builder class for PositionView.
     */
    public static class PositionViewBuilder {
        private Long id;
        private String name;
        private String code;
        private String description;
        private Integer sort;
        private Status status;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        private PositionViewBuilder() {
        }

        public PositionViewBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public PositionViewBuilder name(String name) {
            this.name = name;
            return this;
        }

        public PositionViewBuilder code(String code) {
            this.code = code;
            return this;
        }

        public PositionViewBuilder description(String description) {
            this.description = description;
            return this;
        }

        public PositionViewBuilder sort(Integer sort) {
            this.sort = sort;
            return this;
        }

        public PositionViewBuilder status(Status status) {
            this.status = status;
            return this;
        }

        public PositionViewBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public PositionViewBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public PositionView build() {
            return new PositionView(id, name, code, description, sort, status, createdAt, updatedAt);
        }
    }
}