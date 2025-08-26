package com.onixbyte.helix.domain.biz;

import com.onixbyte.helix.enums.NormalStatus;

import java.io.Serializable;
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
}
