package com.onixbyte.helix.domain.entity;

import com.onixbyte.helix.constant.UserStatus;
import jakarta.persistence.*; // 导入 Jakarta Persistence API
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents a user entity in the Helix system.
 * <p>
 * This entity encapsulates all user-related information including authentication credentials,
 * personal details, contact information, and organisational associations. Users are the core
 * entities that interact with the system and are associated with departments and positions within
 * the organisational hierarchy.
 *
 * @author zihluwang
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(name = "uidx_users_username", columnNames = {"username"}),
                @UniqueConstraint(name = "uidx_users_email", columnNames = {"email"}),
                @UniqueConstraint(name = "uidx_users_region_abbreviation_phone_number", columnNames = {"region_abbreviation", "phone_number"})
        },
        indexes = {@Index(name = "users_username_index", columnList = "username")}
)
public class User {

    /**
     * The unique identifier for the user.
     * <p>
     * This serves as the primary key in the database and is used for all internal references to the
     * user entity. Since the SQL uses `BIGINT PRIMARY KEY` without `SERIAL`, we assume the ID is assigned manually or by an external service.
     */
    @Id
    @Column(nullable = false)
    private Long id;

    /**
     * The unique username for authentication purposes.
     */
    @Column(nullable = false, length = 64)
    private String username;

    /**
     * The encrypted password for user authentication.
     */
    @Column
    private String password;

    /**
     * The user's complete full name.
     */
    @Column(nullable = false, length = 128)
    private String fullName;

    /**
     * The user's email address.
     */
    @Column(length = 128)
    private String email;

    /**
     * The region abbreviation for the user's phone number.
     */
    @Column(length = 10)
    private String regionAbbreviation;

    /**
     * The user's phone number without the country code.
     */
    @Column(length = 32)
    private String phoneNumber;

    /**
     * The URL to the user's avatar image.
     */
    @Column(columnDefinition = "TEXT")
    private String avatarUrl;

    /**
     * The current status of the user account.
     * <p>
     * Mapped to the custom SQL type USER_STATUS.
     */
    @Column(nullable = false)
    @Enumerated
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private UserStatus status;

    /**
     * The identifier of the department to which this user belongs.
     * <p>
     * {@code @ManyToOne} is the standard way to map a foreign key (department_id) in JPA.
     * You might replace this with a direct {@code @ManyToOne} mapping to the Department entity later.
     */
    @Column
    private Long departmentId;

    /**
     * The identifier of the position held by this user.
     */
    @Column
    private Long positionId;

    /**
     * The timestamp when this user record was created.
     */
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * The timestamp when this user record was last updated.
     */
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    // --- JPA Lifecycle Callbacks for Auditing ---

    @PrePersist
    protected void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
        if (this.updatedAt == null) {
            this.updatedAt = LocalDateTime.now();
        }

        // Ensure status default is applied if not set
        if (this.status == null) {
            this.status = UserStatus.ACTIVE;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // --- Constructors, Getters, Setters, and Builder (omitted for brevity) ---

    public User() {
    }

    public User(Long id, String username, String password, String fullName, String email, String regionAbbreviation, String phoneNumber, String avatarUrl, UserStatus status, Long departmentId, Long positionId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.regionAbbreviation = regionAbbreviation;
        this.phoneNumber = phoneNumber;
        this.avatarUrl = avatarUrl;
        this.status = status;
        this.departmentId = departmentId;
        this.positionId = positionId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * Gets the unique identifier for this user.
     *
     * @return the user's unique identifier
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier for this user.
     *
     * @param id the user's unique identifier
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the username for authentication.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username for authentication.
     *
     * @param username the username, must be unique across the system
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the encrypted password.
     *
     * @return the encrypted password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the encrypted password.
     *
     * @param password the encrypted password (never plain text)
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Gets the user's email address.
     *
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email address.
     *
     * @param email the email address, must be unique across the system
     */
    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegionAbbreviation() {
        return regionAbbreviation;
    }

    public void setRegionAbbreviation(String countryCode) {
        this.regionAbbreviation = countryCode;
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

    /**
     * Gets the current status of the user account.
     *
     * @return the user status
     */
    public UserStatus getStatus() {
        return status;
    }

    /**
     * Sets the current status of the user account.
     *
     * @param status the user status
     */
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
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id); // Typically only check the Primary Key for entity equality
    }

    @Override
    public int hashCode() {
        return Objects.hash(id); // Hash code based on the Primary Key
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", departmentId=" + departmentId +
                ", positionId=" + positionId +
                '}';
    }

    /**
     * Creates a new Builder instance for constructing User objects.
     *
     * @return a new UserBuilder instance
     */
    public static UserBuilder builder() {
        return new UserBuilder();
    }

    /**
     * Builder class for constructing User instances with a fluent API.
     * <p>
     * This builder provides a convenient way to construct User objects with optional parameters,
     * following the Builder pattern for improved readability and maintainability.
     */
    public static class UserBuilder {
        private Long id;
        private String username;
        private String password;
        private String fullName;
        private String email;
        private String regionAbbreviation;
        private String phoneNumber;
        private String avatarUrl;
        private UserStatus status;
        private Long departmentId;
        private Long positionId;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

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

        public UserBuilder regionAbbreviation(String regionAbbreviation) {
            this.regionAbbreviation = regionAbbreviation;
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

        public UserBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public UserBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        /**
         * Builds and returns a new User instance with the configured properties.
         *
         * @return a new User instance
         */
        public User build() {
            return new User(id, username, password, fullName, email, regionAbbreviation, phoneNumber, avatarUrl, status, departmentId, positionId, createdAt, updatedAt);
        }
    }
}