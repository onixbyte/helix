package com.onixbyte.helix.domain.view;

import com.onixbyte.helix.constant.UserStatus;
import com.onixbyte.helix.domain.entity.User;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * View object for User entity.
 * <p>
 * This class represents a view of the User entity, providing a data transfer object
 * for user information in the Helix system.
 *
 * @author zihluwang
 * @version 1.0
 * @since 1.0
 */
public class UserView {

    /**
     * The unique identifier for the user.
     */
    private Long id;

    /**
     * The unique username for authentication purposes.
     */
    private String username;

    /**
     * The user's complete full name.
     */
    private String fullName;

    /**
     * The user's email address.
     */
    private String email;

    /**
     * The country code for the user's phone number.
     */
    private String countryCode;

    /**
     * The user's phone number without the country code.
     */
    private String phoneNumber;

    /**
     * The URL to the user's avatar image.
     */
    private String avatarUrl;

    /**
     * The current status of the user account.
     */
    private UserStatus status;

    /**
     * The identifier of the department to which this user belongs.
     */
    private Long departmentId;

    /**
     * The identifier of the position held by this user.
     */
    private Long positionId;

    /**
     * The timestamp when this user record was created.
     */
    private LocalDateTime createdAt;

    /**
     * The timestamp when this user record was last updated.
     */
    private LocalDateTime updatedAt;

    /**
     * Default constructor.
     */
    public UserView() {
    }

    /**
     * Constructor with all fields (excluding password for security).
     */
    public UserView(Long id, String username, String fullName, String email, String countryCode,
                   String phoneNumber, String avatarUrl, UserStatus status, Long departmentId,
                   Long positionId, LocalDateTime createdAt, LocalDateTime updatedAt) {
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
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * Creates a UserView from a User entity (excluding password for security).
     *
     * @param user the User entity
     * @return the UserView object
     */
    public static UserView fromEntity(User user) {
        if (user == null) {
            return null;
        }
        return new UserView(
                user.getId(),
                user.getUsername(),
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
        UserView userView = (UserView) o;
        return Objects.equals(id, userView.id) &&
                Objects.equals(username, userView.username) &&
                Objects.equals(fullName, userView.fullName) &&
                Objects.equals(email, userView.email) &&
                Objects.equals(countryCode, userView.countryCode) &&
                Objects.equals(phoneNumber, userView.phoneNumber) &&
                Objects.equals(avatarUrl, userView.avatarUrl) &&
                status == userView.status &&
                Objects.equals(departmentId, userView.departmentId) &&
                Objects.equals(positionId, userView.positionId) &&
                Objects.equals(createdAt, userView.createdAt) &&
                Objects.equals(updatedAt, userView.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, fullName, email, countryCode, phoneNumber, avatarUrl,
                status, departmentId, positionId, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "UserView{" +
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
     * Creates a new builder instance.
     *
     * @return a new UserViewBuilder
     */
    public static UserViewBuilder builder() {
        return new UserViewBuilder();
    }

    /**
     * Builder class for UserView.
     */
    public static class UserViewBuilder {
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
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        private UserViewBuilder() {
        }

        public UserViewBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public UserViewBuilder username(String username) {
            this.username = username;
            return this;
        }

        public UserViewBuilder fullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public UserViewBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserViewBuilder countryCode(String countryCode) {
            this.countryCode = countryCode;
            return this;
        }

        public UserViewBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public UserViewBuilder avatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
            return this;
        }

        public UserViewBuilder status(UserStatus status) {
            this.status = status;
            return this;
        }

        public UserViewBuilder departmentId(Long departmentId) {
            this.departmentId = departmentId;
            return this;
        }

        public UserViewBuilder positionId(Long positionId) {
            this.positionId = positionId;
            return this;
        }

        public UserViewBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public UserViewBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public UserView build() {
            return new UserView(id, username, fullName, email, countryCode, phoneNumber, avatarUrl,
                    status, departmentId, positionId, createdAt, updatedAt);
        }
    }
}