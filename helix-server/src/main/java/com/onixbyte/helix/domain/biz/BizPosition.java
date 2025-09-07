package com.onixbyte.helix.domain.biz;

import com.onixbyte.helix.constant.NormalStatus;

import java.io.Serializable;
import java.util.Objects;

public class BizPosition implements Serializable {

    private Long id;

    private String name;

    private String code;

    private String description;

    private Integer sortOrder;

    private NormalStatus status;

    public BizPosition() {
    }

    public BizPosition(Long id, String name, String code, String description, Integer sortOrder, NormalStatus status) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.description = description;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        BizPosition that = (BizPosition) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(code, that.code) && Objects.equals(description, that.description) && Objects.equals(sortOrder, that.sortOrder) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code, description, sortOrder, status);
    }

    @Override
    public String toString() {
        return "BizPosition{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", sortOrder=" + sortOrder +
                ", status=" + status +
                '}';
    }

    public static BizPositionBuilder builder() {
        return new BizPositionBuilder();
    }

    public static class BizPositionBuilder {
        private Long id;
        private String name;
        private String code;
        private String description;
        private Integer sortOrder;
        private NormalStatus status;

        private BizPositionBuilder() {
        }

        public BizPositionBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public BizPositionBuilder name(String name) {
            this.name = name;
            return this;
        }

        public BizPositionBuilder code(String code) {
            this.code = code;
            return this;
        }

        public BizPositionBuilder description(String description) {
            this.description = description;
            return this;
        }

        public BizPositionBuilder sortOrder(Integer sortOrder) {
            this.sortOrder = sortOrder;
            return this;
        }

        public BizPositionBuilder status(NormalStatus status) {
            this.status = status;
            return this;
        }

        public BizPosition build() {
            return new BizPosition(id, name, code, description, sortOrder, status);
        }
    }
}