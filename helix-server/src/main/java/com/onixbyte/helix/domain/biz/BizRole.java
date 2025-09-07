package com.onixbyte.helix.domain.biz;

import com.onixbyte.helix.constant.NormalStatus;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class BizRole implements Serializable {

    private Long id;

    private String name;

    private String code;

    private String description;

    private NormalStatus status;

    public BizRole() {
    }

    public BizRole(Long id, String name, String code, String description, NormalStatus status) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.description = description;
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

    public NormalStatus getStatus() {
        return status;
    }

    public void setStatus(NormalStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BizRole bizRole = (BizRole) o;
        return Objects.equals(id, bizRole.id) && Objects.equals(name, bizRole.name) && Objects.equals(code, bizRole.code) && Objects.equals(description, bizRole.description) && status == bizRole.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code, description, status);
    }

    @Override
    public String toString() {
        return "BizRole{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }

    public static BizRoleBuilder builder() {
        return new BizRoleBuilder();
    }

    public static class BizRoleBuilder {
        private Long id;
        private String name;
        private String code;
        private String description;
        private NormalStatus status;

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

        public BizRoleBuilder description(String description) {
            this.description = description;
            return this;
        }

        public BizRoleBuilder status(NormalStatus status) {
            this.status = status;
            return this;
        }

        public BizRole build() {
            return new BizRole(id, name, code, description, status);
        }
    }
}
