package com.onixbyte.helix.domain.view;

import com.onixbyte.helix.constant.Status;
import com.onixbyte.helix.domain.entity.Department;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * View object for Department entity.
 * <p>
 * This class represents the view layer abstraction of a Department, providing a simplified
 * representation suitable for presentation in user interfaces and API responses.
 *
 * @author Zihlu Wang
 * @version 1.0.0
 * @since 1.0.0
 */
public class DepartmentView {

    /**
     * The unique identifier for the department.
     */
    private Long id;

    /**
     * The name of the department.
     */
    private String name;

    /**
     * The identifier of the parent department.
     */
    private Long parentId;

    /**
     * The complete path from root to this department in the hierarchy.
     */
    private String treePath;

    /**
     * The sort order for displaying departments.
     */
    private Integer sort;

    /**
     * The current status of the department.
     */
    private Status status;

    /**
     * The timestamp when this department record was created.
     */
    private LocalDateTime createTime;

    /**
     * The timestamp when this department record was last updated.
     */
    private LocalDateTime updateTime;

    /**
     * Default constructor for serialisation frameworks.
     */
    public DepartmentView() {
    }

    /**
     * Constructs a new DepartmentView with all fields specified.
     *
     * @param id         the unique identifier
     * @param name       the name of the department
     * @param parentId   the identifier of the parent department
     * @param treePath   the complete path from root to this department
     * @param sort       the sort order for displaying departments
     * @param status     the current status
     * @param createTime the creation timestamp
     * @param updateTime the last update timestamp
     */
    public DepartmentView(Long id, String name, Long parentId, String treePath, Integer sort,
                         Status status, LocalDateTime createTime, LocalDateTime updateTime) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.treePath = treePath;
        this.sort = sort;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    /**
     * Creates a DepartmentView from a Department entity.
     *
     * @param entity the Department entity
     * @return a new DepartmentView instance
     */
    public static DepartmentView fromEntity(Department entity) {
        if (entity == null) {
            return null;
        }
        return new DepartmentView(
                entity.getId(),
                entity.getName(),
                entity.getParentId(),
                entity.getTreePath(),
                entity.getSort(),
                entity.getStatus(),
                entity.getCreateTime(),
                entity.getUpdateTime()
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentView that = (DepartmentView) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(parentId, that.parentId) &&
                Objects.equals(treePath, that.treePath) &&
                Objects.equals(sort, that.sort) &&
                status == that.status &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, parentId, treePath, sort, status, createTime, updateTime);
    }

    @Override
    public String toString() {
        return "DepartmentView{" +
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
     * Returns a builder for constructing DepartmentView instances.
     *
     * @return a new DepartmentViewBuilder instance
     */
    public static DepartmentViewBuilder builder() {
        return new DepartmentViewBuilder();
    }

    /**
     * Builder class for constructing {@link DepartmentView} instances.
     * <p>
     * This builder provides a fluent interface for creating DepartmentView objects with optional
     * parameters, following the builder pattern for improved readability and maintainability.
     */
    public static class DepartmentViewBuilder {
        private Long id;
        private String name;
        private Long parentId;
        private String treePath;
        private Integer sort;
        private Status status;
        private LocalDateTime createTime;
        private LocalDateTime updateTime;

        private DepartmentViewBuilder() {
        }

        public DepartmentViewBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public DepartmentViewBuilder name(String name) {
            this.name = name;
            return this;
        }

        public DepartmentViewBuilder parentId(Long parentId) {
            this.parentId = parentId;
            return this;
        }

        public DepartmentViewBuilder treePath(String treePath) {
            this.treePath = treePath;
            return this;
        }

        public DepartmentViewBuilder sort(Integer sort) {
            this.sort = sort;
            return this;
        }

        public DepartmentViewBuilder status(Status status) {
            this.status = status;
            return this;
        }

        public DepartmentViewBuilder createTime(LocalDateTime createTime) {
            this.createTime = createTime;
            return this;
        }

        public DepartmentViewBuilder updateTime(LocalDateTime updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        /**
         * Builds and returns a new DepartmentView instance with the configured parameters.
         *
         * @return a new DepartmentView instance
         */
        public DepartmentView build() {
            return new DepartmentView(id, name, parentId, treePath, sort, status, createTime, updateTime);
        }
    }
}