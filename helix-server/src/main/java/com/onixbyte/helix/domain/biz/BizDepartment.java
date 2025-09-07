package com.onixbyte.helix.domain.biz;

import com.onixbyte.helix.constant.NormalStatus;

import java.util.Objects;

public class BizDepartment {

    private Long id;

    private String name;

    private Long parentId;

    private String treePath;

    private Integer sortOrder;

    private NormalStatus status;

    public BizDepartment() {
    }

    public BizDepartment(Long id, String name, Long parentId, String treePath, Integer sortOrder, NormalStatus status) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.treePath = treePath;
        this.sortOrder = sortOrder;
        this.status = status;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BizDepartment that = (BizDepartment) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(parentId, that.parentId) && Objects.equals(treePath, that.treePath) && Objects.equals(sortOrder, that.sortOrder) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, parentId, treePath, sortOrder, status);
    }

    @Override
    public String toString() {
        return "BizDepartment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                ", treePath='" + treePath + '\'' +
                ", sortOrder=" + sortOrder +
                ", status=" + status +
                '}';
    }

    public static BizDepartmentBuilder builder() {
        return new BizDepartmentBuilder();
    }

    public static class BizDepartmentBuilder {
        private Long id;
        private String name;
        private Long parentId;
        private String treePath;
        private Integer sortOrder;
        private NormalStatus status;

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

        public BizDepartmentBuilder parentId(Long parentId) {
            this.parentId = parentId;
            return this;
        }

        public BizDepartmentBuilder treePath(String treePath) {
            this.treePath = treePath;
            return this;
        }

        public BizDepartmentBuilder sortOrder(Integer sortOrder) {
            this.sortOrder = sortOrder;
            return this;
        }

        public BizDepartmentBuilder status(NormalStatus status) {
            this.status = status;
            return this;
        }

        public BizDepartment build() {
            return new BizDepartment(id, name, parentId, treePath, sortOrder, status);
        }
    }
}
