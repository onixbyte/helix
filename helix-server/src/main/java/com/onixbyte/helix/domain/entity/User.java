package com.onixbyte.helix.domain.entity;

import com.onixbyte.helix.constant.UserStatus;

import java.time.Instant;
import java.util.Objects;

/**
 * Entity representing users within the Helix application.
 * <p>
 * A user represents an individual who can access and interact with the system. Users are the
 * central entity in the application's security and organisational model, containing authentication
 * credentials, personal information, and organisational associations.
 * <p>
 * Each user has a unique username for authentication, personal details such as full name and
 * contact information, and organisational associations through department and position references.
 * Users can have different statuses that control their access to the system.
 * <p>
 * The user entity supports multiple authentication methods through associated
 * {@link UserIdentity} entities, allowing for flexible authentication strategies such as
 * local credentials, OAuth, or other identity providers.
 * <p>
 * Users are associated with departments and positions to establish their role within the
 * organisational hierarchy, and can be assigned multiple roles through the RBAC system to control
 * their permissions and access rights.
 *
 * @author zihluwang
 * @see UserIdentity
 * @see Department
 * @see Position
 * @see Role
 * @see UserStatus
 * @since 1.0.0
 */
public class User {

    /**
     * The unique identifier for this user.
     */
    private Long id;

    /**
     * The unique username for this user.
     * <p>
     * This field is used for authentication and must be unique across all users in the system.
     * It serves as the primary identifier for login purposes.
     */
    private String username;

    /**
     * The encrypted password for this user.
     * <p>
     * This field stores the user's password in an encrypted format. It should never be stored or
     * transmitted in plain text.
     */
    private String password;

    /**
     * The full name of this user.
     * <p>
     * This field contains the user's complete name as it should be displayed throughout the
     * application interface.
     */
    private String fullName;

    /**
     * The email address of this user.
     * <p>
     * This field stores the user's primary email address, which can be used for notifications,
     * password recovery, and other communication purposes.
     */
    private String email;

    /**
     * The country code for the user's phone number.
     * <p>
     * This field stores the international dialling code (e.g., "{@code +44}", "{@code +1}")
     * associated with the user's phone number for proper formatting and
     * international communication.
     */
    private String countryCode;

    /**
     * The phone number of this user.
     * <p>
     * This field stores the user's phone number without the country code, which should be stored
     * separately in the {@code countryCode} field.
     */
    private String phoneNumber;

    /**
     * The URL of the user's avatar image.
     * <p>
     * This field contains a URL pointing to the user's profile picture or avatar image, which can
     * be displayed in the user interface.
     */
    private String avatarUrl;

    /**
     * The current status of this user.
     * <p>
     * This field determines the user's access level and account state, such as active, inactive,
     * or locked.
     */
    private UserStatus status;

    /**
     * The identifier of the department this user belongs to.
     * <p>
     * This field references {@link Department#getId()} and establishes the user's organisational
     * association within the company structure.
     */
    private Long departmentId;

    /**
     * The identifier of the position this user holds.
     * <p>
     * This field references {@link Position#getId()} and defines the user's job role or function
     * within the organisation.
     */
    private Long positionId;

    /**
     * The timestamp when this user was created.
     */
    private Instant createdAt;

    /**
     * The timestamp when this user was last updated.
     */
    private Instant updatedAt;

    /**
     * Default constructor for JPA and serialisation frameworks.
     */
    public User() {
    }

    /**
     * Constructs a new User with all fields specified.
     *
     * @param id           the unique identifier
     * @param username     the unique username for authentication
     * @param password     the encrypted password
     * @param fullName     the user's full name
     * @param email        the user's email address
     * @param countryCode  the country code for the phone number
     * @param phoneNumber  the user's phone number
     * @param avatarUrl    the URL of the user's avatar image
     * @param status       the current user status
     * @param departmentId the identifier of the user's department
     * @param positionId   the identifier of the user's position
     * @param createdAt    the creation timestamp
     * @param updatedAt    the last update timestamp
     */
    public User(Long id, String username, String password, String fullName, String email, String countryCode, String phoneNumber, String avatarUrl, UserStatus status, Long departmentId, Long positionId, Instant createdAt, Instant updatedAt) {
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

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(fullName, user.fullName) &&
                Objects.equals(email, user.email) &&
                Objects.equals(countryCode, user.countryCode) &&
                Objects.equals(phoneNumber, user.phoneNumber) &&
                Objects.equals(avatarUrl, user.avatarUrl) &&
                status == user.status &&
                Objects.equals(departmentId, user.departmentId) &&
                Objects.equals(positionId, user.positionId) &&
                Objects.equals(createdAt, user.createdAt) &&
                Objects.equals(updatedAt, user.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, fullName, email, countryCode, phoneNumber,
                avatarUrl, status, departmentId, positionId, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
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
     * Creates a new builder instance for constructing User objects.
     *
     * @return a new {@link UserBuilder} instance
     */
    public static UserBuilder builder() {
        return new UserBuilder();
    }

    /**
     * Builder class for constructing {@link User} instances.
     * <p>
     * This builder provides a fluent interface for setting user properties and ensures consistent
     * object construction.
     */
    public static class UserBuilder {
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
        private Instant createdAt;
        private Instant updatedAt;

        /**
         * Private constructor to enforce builder pattern usage.
         */
        private UserBuilder() {
        }

        public UserBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public UserBuilder username(String username) {
            this.username = username;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder fullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder countryCode(String countryCode) {
            this.countryCode = countryCode;
            return this;
        }

        public UserBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public UserBuilder avatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
            return this;
        }

        public UserBuilder status(UserStatus status) {
            this.status = status;
            return this;
        }

        public UserBuilder departmentId(Long departmentId) {
            this.departmentId = departmentId;
            return this;
        }

        public UserBuilder positionId(Long positionId) {
            this.positionId = positionId;
            return this;
        }

        public UserBuilder createdAt(Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public UserBuilder updatedAt(Instant updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        /**
         * Builds and returns a new User instance with the configured properties.
         *
         * @return a new {@link User} instance
         */
        public User build() {
            return new User(id, username, password, fullName, email, countryCode, phoneNumber, avatarUrl, status, departmentId, positionId, createdAt, updatedAt);
        }
    }
}
