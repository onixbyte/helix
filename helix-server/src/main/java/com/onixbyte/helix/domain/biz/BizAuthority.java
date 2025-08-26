package com.onixbyte.helix.domain.biz;

import com.onixbyte.helix.enums.NormalStatus;

import java.io.Serializable;
import java.util.Objects;

public class BizAuthority implements Serializable {

    private Long id;

    private String code;

    private String name;

    private String description;

    private NormalStatus status;

    public BizAuthority() {
    }

    public BizAuthority(Long id, String code, String name, String description, NormalStatus status) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        BizAuthority that = (BizAuthority) o;
        return Objects.equals(id, that.id) && Objects.equals(code, that.code) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, name, description, status);
    }

    @Override
    public String toString() {
        return "BizAuthority{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
