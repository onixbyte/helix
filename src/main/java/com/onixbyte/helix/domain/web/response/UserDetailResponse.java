package com.onixbyte.helix.domain.web.response;

import com.onixbyte.helix.constant.UserStatus;

import java.time.LocalDateTime;

public class UserDetailResponse {
    private String id;
    private String username;
    private String fullName;
    private String email;
    private String regionAbbreviation;
    private String phoneNumber;
    private String avatarUrl;
    private UserStatus status;
    private Long departmentId;
    private String departmentName;
    private Long positionId;
    private String positionName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UserDetailResponse() {
    }

    public UserDetailResponse(String id, String username, String fullName, String email, String regionAbbreviation, String phoneNumber, String avatarUrl, UserStatus status, Long departmentId, String departmentName, Long positionId, String positionName, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.regionAbbreviation = regionAbbreviation;
        this.phoneNumber = phoneNumber;
        this.avatarUrl = avatarUrl;
        this.status = status;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.positionId = positionId;
        this.positionName = positionName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegionAbbreviation() {
        return regionAbbreviation;
    }

    public void setRegionAbbreviation(String regionAbbreviation) {
        this.regionAbbreviation = regionAbbreviation;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
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

    public static UserDetailResponseBuilder builder() {
        return new UserDetailResponseBuilder();
    }

    public static class UserDetailResponseBuilder {
        private String id;
        private String username;
        private String fullName;
        private String email;
        private String regionAbbreviation;
        private String phoneNumber;
        private String avatarUrl;
        private UserStatus status;
        private Long departmentId;
        private String departmentName;
        private Long positionId;
        private String positionName;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        private UserDetailResponseBuilder() {
        }

        public UserDetailResponseBuilder id(String id) {
            this.id = id;
            return this;
        }

        public UserDetailResponseBuilder username(String username) {
            this.username = username;
            return this;
        }

        public UserDetailResponseBuilder fullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public UserDetailResponseBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserDetailResponseBuilder regionAbbreviation(String regionAbbreviation) {
            this.regionAbbreviation = regionAbbreviation;
            return this;
        }

        public UserDetailResponseBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public UserDetailResponseBuilder avatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
            return this;
        }

        public UserDetailResponseBuilder status(UserStatus status) {
            this.status = status;
            return this;
        }

        public UserDetailResponseBuilder departmentId(Long departmentId) {
            this.departmentId = departmentId;
            return this;
        }

        public UserDetailResponseBuilder departmentName(String departmentName) {
            this.departmentName = departmentName;
            return this;
        }

        public UserDetailResponseBuilder positionId(Long positionId) {
            this.positionId = positionId;
            return this;
        }

        public UserDetailResponseBuilder positionName(String positionName) {
            this.positionName = positionName;
            return this;
        }

        public UserDetailResponseBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public UserDetailResponseBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public UserDetailResponse build() {
            return new UserDetailResponse(id, username, fullName, email, regionAbbreviation, phoneNumber, avatarUrl, status, departmentId, departmentName, positionId, positionName, createdAt, updatedAt);
        }
    }
}
