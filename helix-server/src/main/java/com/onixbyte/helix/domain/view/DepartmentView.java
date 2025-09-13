package com.onixbyte.helix.domain.view;

import com.onixbyte.helix.constant.NormalStatus;

import java.time.Instant;
import java.util.Objects;

/**
 * View object representing organisational departments for presentation layer operations.
 * <p>
 * This view object encapsulates department data optimised for display purposes, providing a clean
 * interface for department information presentation in user interfaces, API responses, and
 * client-side operations. It contains only the fields necessary for display and user interaction.
 * <p>
 * The DepartmentView is designed to be lightweight and focused on presentation concerns, excluding
 * internal system fields and sensitive information that should not be exposed to client
 * applications or end users.
 * <p>
 * This object is typically used in REST API responses, frontend data binding, organisational chart
 * displays, and any scenario where department information needs to be presented to users in
 * a clean, consumable format.
 *
 * @see com.onixbyte.helix.domain.entity.Department
 * @see com.onixbyte.helix.domain.biz.BizDepartment
 * @see NormalStatus
 * @author zihluwang
 * @since 1.0.0
 */
public class DepartmentView {

    /**
     * The unique identifier for this department.
     */
    private Long id;

    /**
     * The display name of this department.
     * <p>
     * This is the human-readable name that will be displayed to users in interfaces,
     * organisational charts, and selection lists.
     */
    private String name;

    /**
     * The unique identifier of the parent department.
     * <p>
     * This field enables the construction of hierarchical department structures in
     * client applications, such as tree views or organisational charts.
     */
    private Long parentId;

    /**
     * A detailed description of this department.
     * <p>
     * This field provides users with information about the department's purpose, responsibilities,
     * and role within the organisation.
     */
    private String description;

    /**
     * The current status of this department.
     * <p>
     * Indicates whether this department is active and operational for display and
     * interaction purposes.
     */
    private NormalStatus status;

    /**
     * The timestamp when this department was created.
     * <p>
     * This information may be displayed in administrative interfaces for audit and
     * tracking purposes.
     */
    private Instant createdAt;

    /**
     * Default constructor for serialisation frameworks.
     */
    public DepartmentView() {
    }

    /**
     * Constructs a new DepartmentView with all fields specified.
     *
     * @param id          the unique identifier
     * @param name        the display name
     * @param parentId    the parent department identifier
     * @param description the detailed description
     * @param status      the current status
     * @param createdAt   the creation timestamp
     */
    public DepartmentView(Long id, String name, Long parentId, String description,
                          NormalStatus status, Instant createdAt) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
    }

    /**
     * Gets the unique identifier for this department.
     *
     * @return the department ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier for this department.
     *
     * @param id the department ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the display name of this department.
     *
     * @return the department name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the display name of this department.
     *
     * @param name the department name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the unique identifier of the parent department.
     *
     * @return the parent department ID
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * Sets the unique identifier of the parent department.
     *
     * @param parentId the parent department ID to set
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * Gets the detailed description of this department.
     *
     * @return the department description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the detailed description of this department.
     *
     * @param description the department description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the current status of this department.
     *
     * @return the department status
     */
    public NormalStatus getStatus() {
        return status;
    }

    /**
     * Sets the current status of this department.
     *
     * @param status the department status to set
     */
    public void setStatus(NormalStatus status) {
        this.status = status;
    }

    /**
     * Gets the timestamp when this department was created.
     *
     * @return the creation timestamp
     */
    public Instant getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the timestamp when this department was created.
     *
     * @param createdAt the creation timestamp to set
     */
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param obj the reference object with which to compare
     * @return true if this object is the same as the obj argument; false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        DepartmentView that = (DepartmentView) obj;
        return Objects.equals(id, that.id) &&
               Objects.equals(name, that.name) &&
               Objects.equals(parentId, that.parentId) &&
               Objects.equals(description, that.description) &&
               status == that.status &&
               Objects.equals(createdAt, that.createdAt);
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, parentId, description, status, createdAt);
    }

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return "DepartmentView{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", parentId=" + parentId +
               ", description='" + description + '\'' +
               ", status=" + status +
               ", createdAt=" + createdAt +
               '}';
    }

    /**
     * Creates a new builder instance for constructing DepartmentView instances.
     *
     * @return a new DepartmentViewBuilder instance
     */
    public static DepartmentViewBuilder builder() {
        return new DepartmentViewBuilder();
    }

    /**
     * DepartmentViewBuilder class for constructing DepartmentView instances using the builder pattern.
     *
     * <p>This builder provides a fluent interface for creating DepartmentView instances with
     * optional parameters, improving code readability and maintainability.
     */
    public static class DepartmentViewBuilder {
        private Long id;
        private String name;
        private Long parentId;
        private String description;
        private NormalStatus status;
        private Instant createdAt;

        /**
         * Private constructor to enforce builder pattern usage.
         */
        private DepartmentViewBuilder() {
        }

        /**
         * Sets the unique identifier for the department.
         *
         * @param id the department ID
         * @return this DepartmentViewBuilder instance for method chaining
         */
        public DepartmentViewBuilder id(Long id) {
            this.id = id;
            return this;
        }

        /**
         * Sets the display name of the department.
         *
         * @param name the department name
         * @return this DepartmentViewBuilder instance for method chaining
         */
        public DepartmentViewBuilder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets the parent department identifier.
         *
         * @param parentId the parent department ID
         * @return this DepartmentViewBuilder instance for method chaining
         */
        public DepartmentViewBuilder parentId(Long parentId) {
            this.parentId = parentId;
            return this;
        }

        /**
         * Sets the detailed description of the department.
         *
         * @param description the department description
         * @return this DepartmentViewBuilder instance for method chaining
         */
        public DepartmentViewBuilder description(String description) {
            this.description = description;
            return this;
        }

        /**
         * Sets the current status of the department.
         *
         * @param status the department status
         * @return this DepartmentViewBuilder instance for method chaining
         */
        public DepartmentViewBuilder status(NormalStatus status) {
            this.status = status;
            return this;
        }

        /**
         * Sets the timestamp when the department was created.
         *
         * @param createdAt the creation timestamp
         * @return this DepartmentViewBuilder instance for method chaining
         */
        public DepartmentViewBuilder createdAt(Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        /**
         * Builds and returns a new DepartmentView instance with the configured values.
         *
         * @return a new DepartmentView instance
         */
        public DepartmentView build() {
            return new DepartmentView(id, name, parentId, description, status, createdAt);
        }
    }
}
