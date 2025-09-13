package com.onixbyte.helix.domain.entity;

import com.onixbyte.helix.constant.Status;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents a department entity within the organisational hierarchy.
 * <p>
 * This entity models departments as hierarchical structures where each department can have a parent
 * department, creating a tree-like organisational structure. Departments are used to group users
 * and define organisational boundaries within the Helix system.
 *
 * @author zihluwang
 * @version 1.0
 * @since 1.0
 */
public class Department {

    /**
     * The unique identifier for the department.
     * <p>
     * This serves as the primary key in the database and is used for all internal references to the
     * department entity.
     */
    private Long id;

    /**
     * The name of the department.
     * <p>
     * This field contains the human-readable name of the department as it should appear in the
     * organisational chart and user interfaces.
     */
    private String name;

    /**
     * The identifier of the parent department.
     * <p>
     * This field establishes the hierarchical relationship between departments. A null value
     * indicates that this is a root-level department with no parent.
     */
    private Long parentId;

    /**
     * The complete path from root to this department in the hierarchy.
     * <p>
     * This field contains a string representation of the department's position in the
     * organisational tree, typically formatted as a path with separators
     * (e.g., "/root/division/department"). This enables efficient querying of
     * department hierarchies.
     */
    private String treePath;

    /**
     * The sort order for displaying departments.
     * <p>
     * This field determines the order in which departments should be displayed when listed
     * alongside their siblings in the hierarchy. Lower values indicate higher priority in sorting.
     */
    private Integer sort;

    /**
     * The current status of the department.
     * <p>
     * This field determines whether the department is active, inactive, or in any other state as
     * defined by the {@link Status} enumeration.
     */
    private Status status;

    /**
     * The timestamp when this department record was created.
     * <p>
     * This field is automatically set when the department entity is first persisted and provides
     * audit information about when the department was established.
     */
    private LocalDateTime createTime;

    /**
     * The timestamp when this department record was last updated.
     * <p>
     * This field is automatically updated whenever any changes are made to the department entity
     * and provides audit information about the most recent modification.
     */
    private LocalDateTime updateTime;

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

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * Default constructor for Department.
     * <p>
     * Creates a new Department instance with all fields initialised to their default values. This
     * constructor is typically used by JPA and other frameworks for entity instantiation.
     */
    public Department() {
    }

    /**
     * Constructs a new Department with all specified parameters.
     * <p>
     * This constructor allows for the creation of a fully initialised Department entity with all
     * field values provided at instantiation time.
     *
     * @param id         the unique identifier for the department
     * @param name       the name of the department
     * @param parentId   the identifier of the parent department (null for root departments)
     * @param treePath   the complete hierarchical path to this department
     * @param sort       the sort order for display purposes
     * @param status     the current status of the department
     * @param createTime the timestamp when the department was created
     * @param updateTime the timestamp when the department was last updated
     */
    public Department(Long id, String name, Long parentId, String treePath, Integer sort, Status status, LocalDateTime createTime, LocalDateTime updateTime) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.treePath = treePath;
        this.sort = sort;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(parentId, that.parentId) && Objects.equals(treePath, that.treePath) && Objects.equals(sort, that.sort) && status == that.status && Objects.equals(createTime, that.createTime) && Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, parentId, treePath, sort, status, createTime, updateTime);
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                ", treePath='" + treePath + '\'' +
                ", sort=" + sort +
                ", status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    /**
     * Creates a new Builder instance for constructing Department objects.
     *
     * @return a new DepartmentBuilder instance
     */
    public static DepartmentBuilder builder() {
        return new DepartmentBuilder();
    }

    /**
     * Builder class for constructing Department instances with a fluent API.
     * <p>
     * This builder provides a convenient way to construct Department objects with optional
     * parameters, following the Builder pattern for improved readability and maintainability.
     */
    public static class DepartmentBuilder {
        private Long id;
        private String name;
        private Long parentId;
        private String treePath;
        private Integer sort;
        private Status status;
        private LocalDateTime createTime;
        private LocalDateTime updateTime;

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

        public DepartmentBuilder sort(Integer sort) {
            this.sort = sort;
            return this;
        }

        public DepartmentBuilder status(Status status) {
            this.status = status;
            return this;
        }

        public DepartmentBuilder createTime(LocalDateTime createTime) {
            this.createTime = createTime;
            return this;
        }

        public DepartmentBuilder updateTime(LocalDateTime updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        /**
         * Builds and returns a new Department instance with the configured properties.
         *
         * @return a new Department instance
         */
        public Department build() {
            return new Department(id, name, parentId, treePath, sort, status, createTime, updateTime);
        }
    }
}
