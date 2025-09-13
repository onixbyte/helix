package com.onixbyte.helix.domain.entity;

import com.onixbyte.helix.constant.UserStatus;

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
public class User {

    /**
     * The unique identifier for the user.
     * <p>
     * This serves as the primary key in the database and is used for all internal references to the
     * user entity.
     */
    private Long id;

    /**
     * The unique username for authentication purposes.
     * <p>
     * This field must be unique across the system and is used for user login. It should follow the
     * system's username policy regarding length and allowed characters.
     */
    private String username;

    /**
     * The encrypted password for user authentication.
     * <p>
     * This field stores the hashed password and should never contain plain text passwords. The
     * password is encrypted using the system's configured password encoding strategy.
     */
    private String password;

    /**
     * The user's complete full name.
     * <p>
     * This field contains the user's display name as it should appear throughout the application
     * interface and in reports.
     */
    private String fullName;

    /**
     * The user's email address.
     * <p>
     * This field stores the primary email address for the user and is used for notifications,
     * password recovery, and other system communications. The email must be unique across
     * the system.
     */
    private String email;

    /**
     * The country code for the user's phone number.
     * <p>
     * This field stores the international dialling code (e.g., "+44" for UK, "+1" for US) and is
     * used in conjunction with the phone number for complete contact information.
     */
    private String countryCode;

    /**
     * The user's phone number without the country code.
     * <p>
     * This field stores the local phone number and should be used together with the country code
     * to form the complete international phone number.
     */
    private String phoneNumber;

    /**
     * The URL to the user's avatar image.
     * <p>
     * This field contains the complete URL path to the user's profile picture. It may be null if no
     * avatar has been set.
     */
    private String avatarUrl;

    /**
     * The current status of the user account.
     * <p>
     * This field determines whether the user account is active, inactive, or in any other state as
     * defined by the {@link UserStatus} enumeration.
     */
    private UserStatus status;

    /**
     * The identifier of the department to which this user belongs.
     * <p>
     * This field establishes the relationship between the user and their department within the
     * organisational structure. It may be null if the user is not assigned to any department.
     */
    private Long departmentId;

    /**
     * The identifier of the position held by this user.
     * <p>
     * This field establishes the relationship between the user and their job position within
     * the organisation. It may be null if no specific position is assigned.
     */
    private Long positionId;

    /**
     * The timestamp when this user record was created.
     * <p>
     * This field is automatically set when the user entity is first persisted and provides audit
     * information about when the user account was established.
     */
    private LocalDateTime createdAt;

    /**
     * The timestamp when this user record was last updated.
     * <p>
     * This field is automatically updated whenever any changes are made to the user entity and
     * provides audit information about the most recent modification.
     */
    private LocalDateTime updatedAt;

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

    public User() {
    }

    public User(Long id, String username, String password, String fullName, String email, String countryCode, String phoneNumber, String avatarUrl, UserStatus status, Long departmentId, Long positionId, LocalDateTime createdAt, LocalDateTime updatedAt) {
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(fullName, user.fullName) && Objects.equals(email, user.email) && Objects.equals(countryCode, user.countryCode) && Objects.equals(phoneNumber, user.phoneNumber) && Objects.equals(avatarUrl, user.avatarUrl) && status == user.status && Objects.equals(departmentId, user.departmentId) && Objects.equals(positionId, user.positionId) && Objects.equals(createdAt, user.createdAt) && Objects.equals(updatedAt, user.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, fullName, email, countryCode, phoneNumber, avatarUrl, status, departmentId, positionId, createdAt, updatedAt);
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
}
