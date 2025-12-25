package com.onixbyte.helix.domain.view;

import com.onixbyte.helix.constant.Status;
import com.onixbyte.helix.domain.entity.Role;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * View object for Role entity.
 * <p>
 * This class represents a view of the Role entity, providing a data transfer object
 * for role information within the access control system.
 *
 * @author zihluwang
 * @version 1.0
 * @since 1.0
 */
public class RoleView {

    /**
     * The unique identifier for the role.
     */
    private Long id;

    /**
     * The human-readable name of the role.
     */
    private String name;

    /**
     * The unique code identifier for the role.
     */
    private String code;

    /**
     * The sort order for displaying roles.
     */
    private Integer sort;

    /**
     * Indicates whether this role is assigned by default to new users.
     */
    private Boolean defaultValue;

    /**
     * A detailed description of the role's purpose and permissions.
     */
    private String description;

    /**
     * The current status of the role.
     */
    private Status status;

    /**
     * The timestamp when this role record was created.
     */
    private LocalDateTime createdAt;

    /**
     * The timestamp when this role record was last updated.
     */
    private LocalDateTime updatedAt;

    /**
     * Default constructor.
     */
    public RoleView() {
    }

    /**
     * Constructor with all fields.
     */
    public RoleView(Long id, String name, String code, Integer sort, Boolean defaultValue,
                   String description, Status status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.sort = sort;
        this.defaultValue = defaultValue;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * Creates a RoleView from a Role entity.
     *
     * @param role the Role entity
     * @return the RoleView object
     */
    public static RoleView fromEntity(Role role) {
        if (role == null) {
            return null;
        }
        return new RoleView(
                role.getId(),
                role.getName(),
                role.getCode(),
                role.getSort(),
                role.getDefaultValue(),
                role.getDescription(),
                role.getStatus(),
                role.getCreatedAt(),
                role.getUpdatedAt()
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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Boolean getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Boolean defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        RoleView roleView = (RoleView) o;
        return Objects.equals(id, roleView.id) &&
                Objects.equals(name, roleView.name) &&
                Objects.equals(code, roleView.code) &&
                Objects.equals(sort, roleView.sort) &&
                Objects.equals(defaultValue, roleView.defaultValue) &&
                Objects.equals(description, roleView.description) &&
                status == roleView.status &&
                Objects.equals(createdAt, roleView.createdAt) &&
                Objects.equals(updatedAt, roleView.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code, sort, defaultValue, description, status, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "RoleView{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", sort=" + sort +
                ", defaultValue=" + defaultValue +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    /**
     * Creates a new builder instance.
     *
     * @return a new RoleViewBuilder
     */
    public static RoleViewBuilder builder() {
        return new RoleViewBuilder();
    }

    /**
     * Builder class for RoleView.
     */
    public static class RoleViewBuilder {
        private Long id;
        private String name;
        private String code;
        private Integer sort;
        private Boolean defaultValue;
        private String description;
        private Status status;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        private RoleViewBuilder() {
        }

        public RoleViewBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public RoleViewBuilder name(String name) {
            this.name = name;
            return this;
        }

        public RoleViewBuilder code(String code) {
            this.code = code;
            return this;
        }

        public RoleViewBuilder sort(Integer sort) {
            this.sort = sort;
            return this;
        }

        public RoleViewBuilder defaultValue(Boolean defaultValue) {
            this.defaultValue = defaultValue;
            return this;
        }

        public RoleViewBuilder description(String description) {
            this.description = description;
            return this;
        }

        public RoleViewBuilder status(Status status) {
            this.status = status;
            return this;
        }

        public RoleViewBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public RoleViewBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public RoleView build() {
            return new RoleView(id, name, code, sort, defaultValue, description, status, createdAt, updatedAt);
        }
    }
}