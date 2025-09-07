package com.onixbyte.helix.domain.biz;

import com.onixbyte.helix.constant.UserStatus;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class BizUser implements Serializable {

    private Long id;

    private String username;

    private String fullName;

    private String email;

    private String countryCode;

    private String phoneNumber;

    private String avatarUrl;

    private UserStatus status;

    private Long departmentId;

    private Long positionId;

    private List<BizUserIdentity> userIdentities;

    private List<BizRole> roles;

    private List<BizAuthority> authorities;

    public BizUser() {
    }

    public BizUser(
            Long id,
            String username,
            String fullName,
            String email,
            String countryCode,
            String phoneNumber,
            String avatarUrl,
            UserStatus status,
            Long departmentId,
            Long positionId,
            List<BizUserIdentity> userIdentities,
            List<BizRole> roles,
            List<BizAuthority> authorities
    ) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.countryCode = countryCode;
        this.phoneNumber = phoneNumber;
        this.avatarUrl = avatarUrl;
        this.status = status;
        this.departmentId = departmentId;
        this.positionId = positionId;
        this.userIdentities = userIdentities;
        this.roles = roles;
        this.authorities = authorities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
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

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

    public List<BizUserIdentity> getUserIdentities() {
        return userIdentities;
    }

    public void setUserIdentities(List<BizUserIdentity> userIdentities) {
        this.userIdentities = userIdentities;
    }

    public List<BizRole> getRoles() {
        return roles;
    }

    public List<BizAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<BizAuthority> authorities) {
        this.authorities = authorities;
    }

    public void setRoles(List<BizRole> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BizUser bizUser = (BizUser) o;
        return Objects.equals(id, bizUser.id) && Objects.equals(username, bizUser.username) && Objects.equals(fullName, bizUser.fullName) && Objects.equals(email, bizUser.email) && Objects.equals(countryCode, bizUser.countryCode) && Objects.equals(phoneNumber, bizUser.phoneNumber) && Objects.equals(avatarUrl, bizUser.avatarUrl) && status == bizUser.status && Objects.equals(departmentId, bizUser.departmentId) && Objects.equals(positionId, bizUser.positionId) && Objects.equals(userIdentities, bizUser.userIdentities) && Objects.equals(roles, bizUser.roles) && Objects.equals(authorities, bizUser.authorities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, fullName, email, countryCode, phoneNumber, avatarUrl, status, departmentId, positionId, userIdentities, roles, authorities);
    }

    @Override
    public String toString() {
        return "BizUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", status=" + status +
                ", departmentId=" + departmentId +
                ", positionId=" + positionId +
                ", userIdentities=" + userIdentities +
                ", roles=" + roles +
                ", authorities=" + authorities +
                '}';
    }

    public static BizUserBuilder builder() {
        return new BizUserBuilder();
    }

    public static class BizUserBuilder {
        private Long id;
        private String username;
        private String fullName;
        private String email;
        private String countryCode;
        private String phoneNumber;
        private String avatarUrl;
        private UserStatus status;
        private Long departmentId;
        private Long positionId;
        private List<BizUserIdentity> userIdentities;
        private List<BizRole> roles;
        private List<BizAuthority> authorities;

        private BizUserBuilder() {
        }

        public BizUserBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public BizUserBuilder username(String username) {
            this.username = username;
            return this;
        }

        public BizUserBuilder fullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public BizUserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public BizUserBuilder countryCode(String countryCode) {
            this.countryCode = countryCode;
            return this;
        }

        public BizUserBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public BizUserBuilder avatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
            return this;
        }

        public BizUserBuilder status(UserStatus status) {
            this.status = status;
            return this;
        }

        public BizUserBuilder departmentId(Long departmentId) {
            this.departmentId = departmentId;
            return this;
        }

        public BizUserBuilder positionId(Long positionId) {
            this.positionId = positionId;
            return this;
        }

        public BizUserBuilder userIdentities(List<BizUserIdentity> userIdentities) {
            this.userIdentities = userIdentities;
            return this;
        }

        public BizUserBuilder roles(List<BizRole> roles) {
            this.roles = roles;
            return this;
        }

        public BizUserBuilder authorities(List<BizAuthority> authorities) {
            this.authorities = authorities;
            return this;
        }

        public BizUser build() {
            return new BizUser(id, username, fullName, email, countryCode, phoneNumber, avatarUrl,
                    status, departmentId, positionId, userIdentities, roles, authorities);
        }
    }
}
