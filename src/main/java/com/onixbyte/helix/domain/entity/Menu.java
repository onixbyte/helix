package com.onixbyte.helix.domain.entity;

import com.onixbyte.helix.constant.Status;
import com.onixbyte.helix.domain.common.Treeable;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "menus")
public class Menu implements Treeable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column
    private Long parentId;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private Integer sort;

    @Column
    private String path;

    @Column
    private Boolean isExternalLink;

    @Column
    private Boolean isVisible;

    @Column(nullable = false)
    @Enumerated
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private Status status;

    @Column(length = 128)
    private String authorityCode;

    @Column(length = 128)
    private String icon;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    public Menu() {
    }

    public Menu(Long id, String name, Long parentId, String code, Integer sort, String path, Boolean isExternalLink, Boolean isVisible, Status status, String authorityCode, String icon, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.code = code;
        this.sort = sort;
        this.path = path;
        this.isExternalLink = isExternalLink;
        this.isVisible = isVisible;
        this.status = status;
        this.authorityCode = authorityCode;
        this.icon = icon;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getExternalLink() {
        return isExternalLink;
    }

    public void setExternalLink(Boolean externalLink) {
        isExternalLink = externalLink;
    }

    public Boolean getVisible() {
        return isVisible;
    }

    public void setVisible(Boolean visible) {
        isVisible = visible;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Boolean getIsExternalLink() {
        return isExternalLink;
    }

    public void setIsExternalLink(Boolean isExternalLink) {
        this.isExternalLink = isExternalLink;
    }

    public Boolean getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(Boolean isVisible) {
        this.isVisible = isVisible;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getAuthorityCode() {
        return authorityCode;
    }

    public void setAuthorityCode(String authorityCode) {
        this.authorityCode = authorityCode;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return Objects.equals(id, menu.id) && Objects.equals(name, menu.name) && Objects.equals(parentId, menu.parentId) && Objects.equals(code, menu.code) && Objects.equals(sort, menu.sort) && Objects.equals(path, menu.path) && Objects.equals(isExternalLink, menu.isExternalLink) && Objects.equals(isVisible, menu.isVisible) && status == menu.status && Objects.equals(authorityCode, menu.authorityCode) && Objects.equals(icon, menu.icon) && Objects.equals(createdAt, menu.createdAt) && Objects.equals(updatedAt, menu.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, parentId, code, sort, path, isExternalLink, isVisible, status, authorityCode, icon, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                ", code=" + code +
                ", sort=" + sort +
                ", path=" + path +
                ", isExternalLink=" + isExternalLink +
                ", isVisible=" + isVisible +
                ", status=" + status +
                ", authorityCode='" + authorityCode + '\'' +
                ", icon='" + icon + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
