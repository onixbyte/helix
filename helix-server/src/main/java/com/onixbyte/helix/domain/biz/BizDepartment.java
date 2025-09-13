package com.onixbyte.helix.domain.biz;

import com.onixbyte.helix.domain.entity.Department;
import com.onixbyte.helix.constant.Status;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Business object for Department entity.
 * <p>
 * This class represents the business layer abstraction of a Department, providing additional
 * business logic and validation capabilities beyond the basic entity representation.
 *
 * @author Zihlu Wang
 * @version 1.0.0
 * @since 1.0.0
 */
public class BizDepartment {

    private Long id;
    private String name;
    private Long parentId;
    private String treePath;
    private Integer sort;
    private Status status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    /**
     * Default constructor.
     */
    public BizDepartment() {
    }

    /**
     * Constructs a BizDepartment with all fields.
     *
     * @param id         the department ID
     * @param name       the department name
     * @param parentId   the parent department ID
     * @param treePath   the tree path
     * @param sort       the sort order
     * @param status     the department status
     * @param createTime the creation timestamp
     * @param updateTime the last update timestamp
     */
    public BizDepartment(Long id, String name, Long parentId, String treePath, Integer sort,
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
     * Creates a BizDepartment from a Department entity.
     *
     * @param department the Department entity
     * @return the BizDepartment instance
     */
    public static BizDepartment fromEntity(Department department) {
        if (department == null) {
            return null;
        }
        return new BizDepartment(
                department.getId(),
                department.getName(),
                department.getParentId(),
                department.getTreePath(),
                department.getSort(),
                department.getStatus(),
                department.getCreateTime(),
                department.getUpdateTime()
        );
    }

    /**
     * Converts this BizDepartment to a Department entity.
     *
     * @return the Department entity
     */
    public Department asEntity() {
        return Department.builder()
                .id(this.id)
                .name(this.name)
                .parentId(this.parentId)
                .treePath(this.treePath)
                .sort(this.sort)
                .status(this.status)
                .createTime(this.createTime)
                .updateTime(this.updateTime)
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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
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
        BizDepartment that = (BizDepartment) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) &&
                Objects.equals(parentId, that.parentId) && Objects.equals(treePath, that.treePath) &&
                Objects.equals(sort, that.sort) && status == that.status &&
                Objects.equals(createTime, that.createTime) && Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, parentId, treePath, sort, status, createTime, updateTime);
    }

    @Override
    public String toString() {
        return "BizDepartment{" +
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
     * Creates a new BizDepartmentBuilder instance.
     *
     * @return a new BizDepartmentBuilder
     */
    public static BizDepartmentBuilder builder() {
        return new BizDepartmentBuilder();
    }

    /**
     * Builder class for BizDepartment.
     */
    public static class BizDepartmentBuilder {
        private Long id;
        private String name;
        private Long parentId;
        private String treePath;
        private Integer sort;
        private Status status;
        private LocalDateTime createTime;
        private LocalDateTime updateTime;

        private BizDepartmentBuilder() {
        }

        public BizDepartmentBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public BizDepartmentBuilder name(String name) {
            this.name = name;
            return this;
        }

        public BizDepartmentBuilder treePath(String treePath) {
            this.treePath = treePath;
            return this;
        }

        public BizDepartmentBuilder sort(Integer sort) {
            this.sort = sort;
            return this;
        }

        public BizDepartmentBuilder parentId(Long parentId) {
            this.parentId = parentId;
            return this;
        }

        public BizDepartmentBuilder status(Status status) {
            this.status = status;
            return this;
        }

        public BizDepartmentBuilder createTime(LocalDateTime createTime) {
            this.createTime = createTime;
            return this;
        }

        public BizDepartmentBuilder updateTime(LocalDateTime updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        /**
         * Builds the BizDepartment instance.
         *
         * @return the constructed BizDepartment
         */
        public BizDepartment build() {
            return new BizDepartment(id, name, parentId, treePath, sort, status, createTime, updateTime);
        }
    }
}