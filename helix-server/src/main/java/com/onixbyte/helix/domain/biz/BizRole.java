package com.onixbyte.helix.domain.biz;

import com.onixbyte.helix.domain.entity.Role;
import com.onixbyte.helix.constant.Status;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Business object for Role entity.
 * <p>
 * This class represents the business layer abstraction of a Role, providing additional business
 * logic and validation capabilities beyond the basic entity representation.
 *
 * @author Zihlu Wang
 * @version 1.0.0
 * @since 1.0.0
 */
public class BizRole {
    private Long id;
    private String name;
    private String code;
    private Integer sort;
    private Boolean defaultValue;
    private String description;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /**
     * Default constructor.
     */
    public BizRole() {
    }

    /**
     * Constructor with all parameters.
     *
     * @param id           the role ID
     * @param name         the role name
     * @param code         the role code
     * @param sort         the sort order
     * @param defaultValue whether this is a default role
     * @param description  the role description
     * @param status       the role status
     * @param createdAt    the creation timestamp
     * @param updatedAt    the last update timestamp
     */
    public BizRole(Long id, String name, String code, Integer sort, Boolean defaultValue, String description,
                   Status status, LocalDateTime createdAt, LocalDateTime updatedAt) {
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
     * Creates a BizRole from a Role entity.
     *
     * @param role the Role entity
     * @return a new BizRole instance
     */
    public static BizRole fromEntity(Role role) {
        if (role == null) {
            return null;
        }
        return new BizRole(
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

    /**
     * Converts this BizRole to a Role entity.
     *
     * @return a new Role entity
     */
    public Role asEntity() {
        return Role.builder()
                .id(this.id)
                .name(this.name)
                .code(this.code)
                .sort(this.sort)
                .defaultValue(this.defaultValue)
                .description(this.description)
                .status(this.status)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .build();
    }

    // Getters and Setters
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
        BizRole bizRole = (BizRole) o;
        return Objects.equals(id, bizRole.id) &&
                Objects.equals(name, bizRole.name) &&
                Objects.equals(code, bizRole.code) &&
                Objects.equals(sort, bizRole.sort) &&
                Objects.equals(defaultValue, bizRole.defaultValue) &&
                Objects.equals(description, bizRole.description) &&
                status == bizRole.status &&
                Objects.equals(createdAt, bizRole.createdAt) &&
                Objects.equals(updatedAt, bizRole.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code, sort, defaultValue, description, status, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "BizRole{" +
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
     * Creates a new Builder instance for constructing BizRole objects.
     *
     * @return a new BizRoleBuilder instance
     */
    public static BizRoleBuilder builder() {
        return new BizRoleBuilder();
    }

    /**
     * Builder class for constructing BizRole instances with a fluent API.
     */
    public static class BizRoleBuilder {
        private Long id;
        private String name;
        private String code;
        private Integer sort;
        private Boolean defaultValue;
        private String description;
        private Status status;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        private BizRoleBuilder() {
        }

        public BizRoleBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public BizRoleBuilder name(String name) {
            this.name = name;
            return this;
        }

        public BizRoleBuilder code(String code) {
            this.code = code;
            return this;
        }

        public BizRoleBuilder sort(Integer sort) {
            this.sort = sort;
            return this;
        }

        public BizRoleBuilder defaultValue(Boolean defaultValue) {
            this.defaultValue = defaultValue;
            return this;
        }

        public BizRoleBuilder description(String description) {
            this.description = description;
            return this;
        }

        public BizRoleBuilder status(Status status) {
            this.status = status;
            return this;
        }

        public BizRoleBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public BizRoleBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        /**
         * Builds and returns a new BizRole instance with the configured properties.
         *
         * @return a new BizRole instance
         */
        public BizRole build() {
            return new BizRole(id, name, code, sort, defaultValue, description, status, createdAt, updatedAt);
        }
    }
}