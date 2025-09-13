package com.onixbyte.helix.domain.view;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * View object representing a role for frontend presentation. This class encapsulates role data
 * optimised for client-side consumption, providing a clean interface between the presentation layer
 * and business logic.
 * <p>
 * The RoleView class contains essential role information suitable for display purposes, excluding
 * sensitive or internal system data. It serves as a data transfer object (DTO) for role-related
 * information in API responses and frontend interactions.
 * <p>
 * This class follows the RoleViewBuilder pattern for flexible object construction and includes
 * standard object methods for proper equality, hashing, and string representation.
 *
 * @see com.onixbyte.helix.domain.entity.Role
 * @see com.onixbyte.helix.domain.biz.BizRole
 * @author OnixByte Development Team
 * @since 1.0.0
 */
public class RoleView {

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
     * Default constructor for RoleView.
     */
    public RoleView() {
    }

    /**
     * Constructs a RoleView with the specified parameters.
     *
     * @param id          the unique identifier for the role
     * @param name        the name of the role
     * @param code        the unique code identifier for the role
     * @param description a detailed description of the role
     * @param active      whether the role is currently active
     * @param createdAt   the timestamp when this role was created
     * @param updatedAt   the timestamp when this role was last updated
     */
    public RoleView(Long id, String name, String code, String description, Boolean active,
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
        RoleView roleView = (RoleView) obj;
        return Objects.equals(id, roleView.id) &&
               Objects.equals(name, roleView.name) &&
               Objects.equals(code, roleView.code) &&
               Objects.equals(description, roleView.description) &&
               Objects.equals(active, roleView.active) &&
               Objects.equals(createdAt, roleView.createdAt) &&
               Objects.equals(updatedAt, roleView.updatedAt);
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
        return "RoleView{" +
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
     * Creates a new RoleViewBuilder instance for constructing RoleView objects.
     *
     * @return a new RoleViewBuilder instance
     */
    public static RoleViewBuilder builder() {
        return new RoleViewBuilder();
    }

    /**
     * RoleViewBuilder class for constructing RoleView instances with a fluent API.
     */
    public static class RoleViewBuilder {
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
         * @return this RoleViewBuilder instance for method chaining
         */
        public RoleViewBuilder id(Long id) {
            this.id = id;
            return this;
        }

        /**
         * Sets the name of the role.
         *
         * @param name the role name
         * @return this RoleViewBuilder instance for method chaining
         */
        public RoleViewBuilder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets the unique code identifier for the role.
         *
         * @param code the role code
         * @return this RoleViewBuilder instance for method chaining
         */
        public RoleViewBuilder code(String code) {
            this.code = code;
            return this;
        }

        /**
         * Sets the detailed description of the role.
         *
         * @param description the role description
         * @return this RoleViewBuilder instance for method chaining
         */
        public RoleViewBuilder description(String description) {
            this.description = description;
            return this;
        }

        /**
         * Sets whether the role is currently active.
         *
         * @param active the active status
         * @return this RoleViewBuilder instance for method chaining
         */
        public RoleViewBuilder active(Boolean active) {
            this.active = active;
            return this;
        }

        /**
         * Sets the timestamp when this role was created.
         *
         * @param createdAt the creation timestamp
         * @return this RoleViewBuilder instance for method chaining
         */
        public RoleViewBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        /**
         * Sets the timestamp when this role was last updated.
         *
         * @param updatedAt the last update timestamp
         * @return this RoleViewBuilder instance for method chaining
         */
        public RoleViewBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        /**
         * Builds and returns a new RoleView instance with the configured properties.
         *
         * @return a new RoleView instance
         */
        public RoleView build() {
            return new RoleView(id, name, code, description, active, createdAt, updatedAt);
        }
    }
}
