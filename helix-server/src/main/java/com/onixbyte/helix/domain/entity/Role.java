package com.onixbyte.helix.domain.entity;

import com.onixbyte.helix.constant.Status;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents a role entity within the access control system.
 * <p>
 * Roles define sets of permissions and responsibilities that can be assigned to users. They form
 * the foundation of the role-based access control (RBAC) system, allowing for flexible and scalable
 * permission management across the Helix application.
 *
 * @author zihluwang
 * @version 1.0
 * @since 1.0
 */
public class Role {

    /**
     * The unique identifier for the role.
     * <p>
     * This serves as the primary key in the database and is used for all internal references to the
     * role entity.
     */
    private Long id;

    /**
     * The human-readable name of the role.
     * <p>
     * This field contains the display name of the role as it should appear in user interfaces and
     * administrative panels.
     */
    private String name;

    /**
     * The unique code identifier for the role.
     * <p>
     * This field contains a system-level identifier that is typically used in code
     * and configuration. It should be unique across all roles and follow a consistent
     * naming convention.
     */
    private String code;

    /**
     * The sort order for displaying roles.
     * <p>
     * This field determines the order in which roles should be displayed in lists and
     * selection interfaces. Lower values indicate higher priority in sorting.
     */
    private Integer sort;

    /**
     * Indicates whether this role is assigned by default to new users.
     * <p>
     * When set to true, this role will be automatically assigned to newly created user accounts.
     * This is useful for defining baseline permissions that all users should have.
     */
    private Boolean defaultValue;

    /**
     * A detailed description of the role's purpose and permissions.
     * <p>
     * This field provides additional context about what the role represents and what capabilities
     * it grants to users who are assigned to it.
     */
    private String description;

    /**
     * The current status of the role.
     * <p>
     * This field determines whether the role is active, inactive, or in any other state as defined
     * by the {@link Status} enumeration.
     */
    private Status status;

    /**
     * The timestamp when this role record was created.
     * <p>
     * This field is automatically set when the role entity is first persisted and provides audit
     * information about when the role was established.
     */
    private LocalDateTime createdAt;

    /**
     * The timestamp when this role record was last updated.
     * <p>
     * This field is automatically updated whenever any changes are made to the role entity and
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

    public Role() {
    }

    public Role(Long id, String name, String code, Integer sort, Boolean defaultValue, String description, Status status, LocalDateTime createdAt, LocalDateTime updatedAt) {
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id) && Objects.equals(name, role.name) && Objects.equals(code, role.code) && Objects.equals(sort, role.sort) && Objects.equals(defaultValue, role.defaultValue) && Objects.equals(description, role.description) && status == role.status && Objects.equals(createdAt, role.createdAt) && Objects.equals(updatedAt, role.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code, sort, defaultValue, description, status, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "Role{" +
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
     * Creates a new Builder instance for constructing Role objects.
     *
     * @return a new RoleBuilder instance
     */
    public static RoleBuilder builder() {
        return new RoleBuilder();
    }

    /**
     * Builder class for constructing Role instances with a fluent API.
     * <p>
     * This builder provides a convenient way to construct Role objects with optional parameters,
     * following the Builder pattern for improved readability and maintainability.
     */
    public static class RoleBuilder {
        private Long id;
        private String name;
        private String code;
        private Integer sort;
        private Boolean defaultValue;
        private String description;
        private Status status;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        private RoleBuilder() {
        }

        public RoleBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public RoleBuilder name(String name) {
            this.name = name;
            return this;
        }

        public RoleBuilder code(String code) {
            this.code = code;
            return this;
        }

        public RoleBuilder sort(Integer sort) {
            this.sort = sort;
            return this;
        }

        public RoleBuilder defaultValue(Boolean defaultValue) {
            this.defaultValue = defaultValue;
            return this;
        }

        public RoleBuilder description(String description) {
            this.description = description;
            return this;
        }

        public RoleBuilder status(Status status) {
            this.status = status;
            return this;
        }

        public RoleBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public RoleBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        /**
         * Builds and returns a new Role instance with the configured properties.
         *
         * @return a new Role instance
         */
        public Role build() {
            return new Role(id, name, code, sort, defaultValue, description, status, createdAt, updatedAt);
        }
    }
}
