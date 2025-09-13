package com.onixbyte.helix.domain.biz;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Business object representing a role within the system. This class encapsulates all role-related
 * data and business logic, serving as the primary data structure for role operations in the
 * service layer.
 * <p>
 * The BizRole class contains comprehensive role information including identification, metadata,
 * and audit fields. It is designed to support complex business operations while maintaining data
 * integrity and providing a clear separation between business logic and presentation concerns.
 * <p>
 * This class follows the BizRoleBuilder pattern for flexible object construction and includes standard
 * object methods for proper equality, hashing, and string representation.
 *
 * @see com.onixbyte.helix.domain.entity.Role
 * @see com.onixbyte.helix.domain.view.RoleView
 * @author OnixByte Development Team
 * @since 1.0.0
 */
public class BizRole {

    /**
     * The unique identifier for the role.
     */
    private Long id;

    /**
     * The name of the role.
     */
    private String name;

    /**
     * The unique code identifier for the role.
     */
    private String code;

    /**
     * A detailed description of the role and its responsibilities.
     */
    private String description;

    /**
     * Indicates whether the role is currently active in the system.
     */
    private Boolean active;

    /**
     * The timestamp when this role was created.
     */
    private LocalDateTime createdAt;

    /**
     * The timestamp when this role was last updated.
     */
    private LocalDateTime updatedAt;

    /**
     * Default constructor for BizRole.
     */
    public BizRole() {
    }

    /**
     * Constructs a BizRole with the specified parameters.
     *
     * @param id          the unique identifier for the role
     * @param name        the name of the role
     * @param code        the unique code identifier for the role
     * @param description a detailed description of the role
     * @param active      whether the role is currently active
     * @param createdAt   the timestamp when this role was created
     * @param updatedAt   the timestamp when this role was last updated
     */
    public BizRole(Long id, String name, String code, String description, Boolean active,
                   LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.description = description;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * Gets the unique identifier for the role.
     *
     * @return the role ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the role.
     *
     * @param id the role ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the role.
     *
     * @return the role name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the role.
     *
     * @param name the role name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the unique code identifier for the role.
     *
     * @return the role code
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the unique code identifier for the role.
     *
     * @param code the role code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets the detailed description of the role.
     *
     * @return the role description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the detailed description of the role.
     *
     * @param description the role description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets whether the role is currently active.
     *
     * @return true if the role is active, false otherwise
     */
    public Boolean getActive() {
        return active;
    }

    /**
     * Sets whether the role is currently active.
     *
     * @param active the active status to set
     */
    public void setActive(Boolean active) {
        this.active = active;
    }

    /**
     * Gets the timestamp when this role was created.
     *
     * @return the creation timestamp
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the timestamp when this role was created.
     *
     * @param createdAt the creation timestamp to set
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets the timestamp when this role was last updated.
     *
     * @return the last update timestamp
     */
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets the timestamp when this role was last updated.
     *
     * @param updatedAt the last update timestamp to set
     */
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param obj the reference object with which to compare
     * @return true if this object is the same as the obj argument; false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BizRole bizRole = (BizRole) obj;
        return Objects.equals(id, bizRole.id) &&
               Objects.equals(name, bizRole.name) &&
               Objects.equals(code, bizRole.code) &&
               Objects.equals(description, bizRole.description) &&
               Objects.equals(active, bizRole.active) &&
               Objects.equals(createdAt, bizRole.createdAt) &&
               Objects.equals(updatedAt, bizRole.updatedAt);
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, code, description, active, createdAt, updatedAt);
    }

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return "BizRole{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", code='" + code + '\'' +
               ", description='" + description + '\'' +
               ", active=" + active +
               ", createdAt=" + createdAt +
               ", updatedAt=" + updatedAt +
               '}';
    }

    /**
     * Creates a new BizRoleBuilder instance for constructing BizRole objects.
     *
     * @return a new BizRoleBuilder instance
     */
    public static BizRoleBuilder builder() {
        return new BizRoleBuilder();
    }

    /**
     * BizRoleBuilder class for constructing BizRole instances with a fluent API.
     */
    public static class BizRoleBuilder {
        private Long id;
        private String name;
        private String code;
        private String description;
        private Boolean active;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        /**
         * Sets the unique identifier for the role.
         *
         * @param id the role ID
         * @return this BizRoleBuilder instance for method chaining
         */
        public BizRoleBuilder id(Long id) {
            this.id = id;
            return this;
        }

        /**
         * Sets the name of the role.
         *
         * @param name the role name
         * @return this BizRoleBuilder instance for method chaining
         */
        public BizRoleBuilder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets the unique code identifier for the role.
         *
         * @param code the role code
         * @return this BizRoleBuilder instance for method chaining
         */
        public BizRoleBuilder code(String code) {
            this.code = code;
            return this;
        }

        /**
         * Sets the detailed description of the role.
         *
         * @param description the role description
         * @return this BizRoleBuilder instance for method chaining
         */
        public BizRoleBuilder description(String description) {
            this.description = description;
            return this;
        }

        /**
         * Sets whether the role is currently active.
         *
         * @param active the active status
         * @return this BizRoleBuilder instance for method chaining
         */
        public BizRoleBuilder active(Boolean active) {
            this.active = active;
            return this;
        }

        /**
         * Sets the timestamp when this role was created.
         *
         * @param createdAt the creation timestamp
         * @return this BizRoleBuilder instance for method chaining
         */
        public BizRoleBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        /**
         * Sets the timestamp when this role was last updated.
         *
         * @param updatedAt the last update timestamp
         * @return this BizRoleBuilder instance for method chaining
         */
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
            return new BizRole(id, name, code, description, active, createdAt, updatedAt);
        }
    }
}