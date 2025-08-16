package com.onixbyte.onixboot.dataset.biz;

import com.onixbyte.onixboot.enums.UserStatus;

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

    public BizUser() {
    }

    public BizUser(Long id, String username, String fullName, String email, String countryCode, String phoneNumber, String avatarUrl, UserStatus status, Long departmentId, Long positionId, List<BizUserIdentity> userIdentities) {
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BizUser bizUser = (BizUser) o;
        return Objects.equals(id, bizUser.id) &&
                Objects.equals(username, bizUser.username) &&
                Objects.equals(fullName, bizUser.fullName) &&
                Objects.equals(email, bizUser.email) &&
                Objects.equals(countryCode, bizUser.countryCode) &&
                Objects.equals(phoneNumber, bizUser.phoneNumber) &&
                Objects.equals(avatarUrl, bizUser.avatarUrl) &&
                status == bizUser.status &&
                Objects.equals(departmentId, bizUser.departmentId) &&
                Objects.equals(positionId, bizUser.positionId) &&
                Objects.equals(userIdentities, bizUser.userIdentities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, fullName, email, countryCode, phoneNumber, avatarUrl, status, departmentId, positionId, userIdentities);
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
                '}';
    }
}
