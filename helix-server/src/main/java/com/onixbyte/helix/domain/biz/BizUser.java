package com.onixbyte.helix.domain.biz;

import com.onixbyte.helix.domain.entity.User;
import com.onixbyte.helix.constant.UserStatus;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Business object for User entity.
 * <p>
 * This class represents the business layer abstraction of a User, providing additional business
 * logic and validation capabilities beyond the basic entity representation.
 *
 * @author Zihlu Wang
 * @version 1.0.0
 * @since 1.0.0
 */
public class BizUser {
    private Long id;
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String countryCode;
    private String phoneNumber;
    private String avatarUrl;
    private UserStatus status;
    private Long departmentId;
    private Long positionId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /**
     * Default constructor.
     */
    public BizUser() {
    }

    /**
     * Constructor with all parameters.
     *
     * @param id           the user ID
     * @param username     the username
     * @param password     the password
     * @param fullName     the full name
     * @param email        the email address
     * @param countryCode  the country code
     * @param phoneNumber  the phone number
     * @param avatarUrl    the avatar URL
     * @param status       the user status
     * @param departmentId the department ID
     * @param positionId   the position ID
     * @param createdAt    the creation timestamp
     * @param updatedAt    the last update timestamp
     */
    public BizUser(Long id, String username, String password, String fullName,
                   String email, String countryCode, String phoneNumber, String avatarUrl,
                   UserStatus status, Long departmentId, Long positionId,
                   LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.countryCode = countryCode;
        this.phoneNumber = phoneNumber;
        this.avatarUrl = avatarUrl;
        this.status = status;
        this.departmentId = departmentId;
        this.positionId = positionId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * Creates a BizUser from a User entity.
     *
     * @param user the User entity
     * @return a new BizUser instance
     */
    public static BizUser fromEntity(User user) {
        if (user == null) {
            return null;
        }
        return new BizUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getFullName(),
                user.getEmail(),
                user.getCountryCode(),
                user.getPhoneNumber(),
                user.getAvatarUrl(),
                user.getStatus(),
                user.getDepartmentId(),
                user.getPositionId(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    /**
     * Converts this BizUser to a User entity.
     *
     * @return a new User entity
     */
    public User asEntity() {
        return User.builder()
                .id(this.id)
                .username(this.username)
                .password(this.password)
                .fullName(this.fullName)
                .email(this.email)
                .countryCode(this.countryCode)
                .phoneNumber(this.phoneNumber)
                .avatarUrl(this.avatarUrl)
                .status(this.status)
                .departmentId(this.departmentId)
                .positionId(this.positionId)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .build();
    }

    // Getters and Setters
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BizUser bizUser = (BizUser) o;
        return Objects.equals(id, bizUser.id) &&
                Objects.equals(username, bizUser.username) &&
                Objects.equals(password, bizUser.password) &&
                Objects.equals(fullName, bizUser.fullName) &&
                Objects.equals(email, bizUser.email) &&
                Objects.equals(countryCode, bizUser.countryCode) &&
                Objects.equals(phoneNumber, bizUser.phoneNumber) &&
                Objects.equals(avatarUrl, bizUser.avatarUrl) &&
                status == bizUser.status &&
                Objects.equals(departmentId, bizUser.departmentId) &&
                Objects.equals(positionId, bizUser.positionId) &&
                Objects.equals(createdAt, bizUser.createdAt) &&
                Objects.equals(updatedAt, bizUser.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, fullName, email, countryCode, phoneNumber, avatarUrl, status, departmentId, positionId, createdAt, updatedAt);
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
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    /**
     * Creates a new Builder instance for constructing BizUser objects.
     *
     * @return a new BizUserBuilder instance
     */
    public static BizUserBuilder builder() {
        return new BizUserBuilder();
    }

    /**
     * Builder class for constructing BizUser instances with a fluent API.
     */
    public static class BizUserBuilder {
        private Long id;
        private String username;
        private String password;
        private String fullName;
        private String email;
        private String countryCode;
        private String phoneNumber;
        private String avatarUrl;
        private UserStatus status;
        private Long departmentId;
        private Long positionId;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

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

        public BizUserBuilder password(String password) {
            this.password = password;
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

        public BizUserBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public BizUserBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        /**
         * Builds and returns a new BizUser instance with the configured properties.
         *
         * @return a new BizUser instance
         */
        public BizUser build() {
            return new BizUser(id, username, password, fullName, email, countryCode, phoneNumber, avatarUrl, status, departmentId, positionId, createdAt, updatedAt);
        }
    }
}