package com.onixbyte.helix.domain.entity;

import com.onixbyte.helix.constant.NormalStatus;

import java.time.Instant;
import java.util.Objects;

/**
 * Entity representing organisational departments within the Helix application.
 * <p>
 * A department represents an organisational unit or division within a company or institution.
 * Departments are organised in a hierarchical tree structure, allowing for nested sub-departments
 * and complex organisational hierarchies.
 * <p>
 * The department hierarchy is maintained through parent-child relationships using the
 * {@code parentId} field and materialised path pattern via the {@code treePath} field for efficient
 * tree operations and queries.
 * <p>
 * Each department has a sort order for consistent display ordering within the same
 * hierarchical level, and a status indicating whether the department is currently active within
 * the organisation.
 *
 * @author zihluwang
 * @see User
 * @see Position
 * @see NormalStatus
 * @since 1.0.0
 */
public class Department {

    /**
     * The unique identifier for this department.
     */
    private Long id;

    /**
     * The name of this department.
     * <p>
     * This should be a descriptive name that clearly identifies the department's purpose or
     * function within the organisation.
     */
    private String name;

    /**
     * The identifier of the parent department in the hierarchy.
     * <p>
     * This field is null for root-level departments and contains the ID of the parent department
     * for sub-departments.
     */
    private Long parentId;

    /**
     * The materialised path representing this department's position in the tree.
     * <p>
     * This field contains a path string that represents the full hierarchy from root to
     * this department, enabling efficient tree queries and operations without recursive
     * database calls.
     */
    private String treePath;

    /**
     * The sort order for this department within its hierarchical level.
     * <p>
     * Used to maintain consistent ordering when displaying departments at the same level in
     * the hierarchy.
     */
    private Integer sortOrder;

    /**
     * The current status of this department.
     * <p>
     * Determines whether this department is active and operational within the organisation.
     */
    private NormalStatus status;

    /**
     * The timestamp when this department was created.
     */
    private Instant createdAt;

    /**
     * The timestamp when this department was last updated.
     */
    private Instant updatedAt;

    /**
     * Default constructor for JPA and serialization frameworks.
     */
    public Department() {
    }

    /**
     * Constructs a new Department with all fields specified.
     *
     * @param id        the unique identifier
     * @param name      the department name
     * @param parentId  the parent department ID (null for root departments)
     * @param treePath  the materialised path in the department hierarchy
     * @param sortOrder the sort order within the hierarchical level
     * @param status    the current status
     * @param createdAt the creation timestamp
     * @param updatedAt the last update timestamp
     */
    public Department(Long id, String name, Long parentId, String treePath, Integer sortOrder, NormalStatus status, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.treePath = treePath;
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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getTreePath() {
        return treePath;
    }

    public void setTreePath(String treePath) {
        this.treePath = treePath;
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
        Department that = (Department) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(parentId, that.parentId) &&
                Objects.equals(treePath, that.treePath) &&
                Objects.equals(sortOrder, that.sortOrder) &&
                status == that.status &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, parentId, treePath, sortOrder, status, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                ", treePath='" + treePath + '\'' +
                ", sortOrder=" + sortOrder +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    /**
     * Creates a new builder instance for constructing Department objects.
     *
     * @return a new {@link DepartmentBuilder} instance
     */
    public static DepartmentBuilder builder() {
        return new DepartmentBuilder();
    }

    /**
     * Builder class for constructing {@link Department} instances.
     * <p>
     * This builder provides a fluent interface for setting department properties and ensures
     * consistent object construction.
     */
    public static class DepartmentBuilder {
        private Long id;
        private String name;
        private Long parentId;
        private String treePath;
        private Integer sortOrder;
        private NormalStatus status;
        private Instant createdAt;
        private Instant updatedAt;

        /**
         * Private constructor to enforce builder pattern usage.
         */
        private DepartmentBuilder() {
        }

        public DepartmentBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public DepartmentBuilder name(String name) {
            this.name = name;
            return this;
        }

        public DepartmentBuilder parentId(Long parentId) {
            this.parentId = parentId;
            return this;
        }

        public DepartmentBuilder treePath(String treePath) {
            this.treePath = treePath;
            return this;
        }

        public DepartmentBuilder sortOrder(Integer sortOrder) {
            this.sortOrder = sortOrder;
            return this;
        }

        public DepartmentBuilder status(NormalStatus status) {
            this.status = status;
            return this;
        }

        public DepartmentBuilder createdAt(Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public DepartmentBuilder updatedAt(Instant updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        /**
         * Builds and returns a new Department instance with the configured properties.
         *
         * @return a new {@link Department} instance
         */
        public Department build() {
            return new Department(id, name, parentId, treePath, sortOrder, status, createdAt, updatedAt);
        }
    }
}
