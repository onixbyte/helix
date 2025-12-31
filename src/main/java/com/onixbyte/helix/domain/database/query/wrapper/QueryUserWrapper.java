package com.onixbyte.helix.domain.database.query.wrapper;

import com.onixbyte.helix.enumeration.UserStatus;

import java.time.LocalDateTime;

public class QueryUserWrapper {
    private Long departmentId;
    private String username;
    private String regionAbbreviation;
    private String phoneNumber;
    private UserStatus status;
    private LocalDateTime createdAtStart;
    private LocalDateTime createdAtEnd;

    public QueryUserWrapper() {
    }

    public QueryUserWrapper(Long departmentId, String username, String regionAbbreviation, String phoneNumber, UserStatus status, LocalDateTime createdAtStart, LocalDateTime createdAtEnd) {
        this.departmentId = departmentId;
        this.username = username;
        this.regionAbbreviation = regionAbbreviation;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.createdAtStart = createdAtStart;
        this.createdAtEnd = createdAtEnd;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAtStart() {
        return createdAtStart;
    }

    public void setCreatedAtStart(LocalDateTime createdAtStart) {
        this.createdAtStart = createdAtStart;
    }

    public LocalDateTime getCreatedAtEnd() {
        return createdAtEnd;
    }

    public void setCreatedAtEnd(LocalDateTime createdAtEnd) {
        this.createdAtEnd = createdAtEnd;
    }
}
