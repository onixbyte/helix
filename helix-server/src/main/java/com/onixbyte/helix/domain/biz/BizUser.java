package com.onixbyte.helix.domain.biz;

import java.time.Instant;
import java.util.Objects;

/**
 * Business object representing user information within the Helix application.
 * <p>
 * This business object encapsulates user data for business logic operations, providing a clean
 * interface for user management operations within the service layer. It serves as a data transfer
 * object between different application layers whilst maintaining the integrity of user information.
 * <p>
 * The BizUser contains all information necessary for business operations related to user
 * management, authentication, and authorisation. It excludes sensitive information such as
 * passwords, which are handled separately through secure authentication mechanisms.
 * <p>
 * This object is designed to be used in business logic implementations, service layer operations,
 * and as a foundation for creating view objects or API responses that require user information.
 *
 * @author zihluwang
 * @see com.onixbyte.helix.domain.entity.User
 * @since 1.0.0
 */
public class BizUser {

    /**
     * The unique identifier for the user.
     * <p>
     * This field serves as the primary key for user identification throughout the system and is
     * used for establishing relationships with other entities.
     */
    private Long id;

    /**
     * The unique username for the user account.
     * <p>
     * This field represents the user's chosen identifier for authentication and display purposes.
     * It must be unique across the entire system to ensure proper user identification.
     */
    private String username;

    /**
     * The user's email address.
     * <p>
     * This field stores the user's primary email address, which is used for communication,
     * notifications, and as an alternative authentication method. Email addresses must be unique
     * across the system.
     */
    private String email;

    /**
     * The user's first name.
     * <p>
     * This field contains the user's given name, used for personalisation and display purposes
     * throughout the application interface.
     */
    private String firstName;

    /**
     * The user's last name.
     * <p>
     * This field contains the user's family name, used in conjunction with the first name for
     * complete user identification and display purposes.
     */
    private String lastName;

    /**
     * The user's phone number.
     * <p>
     * This field stores the user's contact phone number, which may be used for two-factor
     * authentication, notifications, or emergency contact purposes.
     */
    private String phoneNumber;

    /**
     * Indicates whether the user account is currently active.
     * <p>
     * This field controls user access to the system. Inactive users cannot authenticate or
     * perform operations, providing a mechanism for temporary or permanent account suspension.
     */
    private Boolean active;

    /**
     * The timestamp when the user account was created.
     * <p>
     * This field tracks when the user first registered with the system, useful for audit trails,
     * analytics, and compliance reporting.
     */
    private Instant createdAt;

    /**
     * The timestamp when the user account was last updated.
     * <p>
     * This field tracks when any user information was last modified, enabling change tracking
     * and audit capabilities.
     */
    private Instant updatedAt;

    /**
     * Default constructor for serialisation frameworks.
     */
    public BizUser() {
    }

    /**
     * Constructs a new BizUser with all fields specified.
     *
     * @param id          the user identifier
     * @param username    the username
     * @param email       the email address
     * @param firstName   the first name
     * @param lastName    the last name
     * @param phoneNumber the phone number
     * @param active      whether the user is active
     * @param createdAt   the creation timestamp
     * @param updatedAt   the last update timestamp
     */
    public BizUser(Long id, String username, String email, String firstName, String lastName,
                   String phoneNumber, Boolean active, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.active = active;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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
        BizUser bizUser = (BizUser) o;
        return Objects.equals(id, bizUser.id) &&
                Objects.equals(username, bizUser.username) &&
                Objects.equals(email, bizUser.email) &&
                Objects.equals(firstName, bizUser.firstName) &&
                Objects.equals(lastName, bizUser.lastName) &&
                Objects.equals(phoneNumber, bizUser.phoneNumber) &&
                Objects.equals(active, bizUser.active) &&
                Objects.equals(createdAt, bizUser.createdAt) &&
                Objects.equals(updatedAt, bizUser.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, firstName, lastName, phoneNumber, active,
                createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "BizUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", active=" + active +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    /**
     * Creates a new builder instance for constructing BizUser instances.
     *
     * @return a new BizUserBuilder instance
     */
    public static BizUserBuilder builder() {
        return new BizUserBuilder();
    }

    /**
     * Builder class for constructing BizUser instances using the builder pattern.
     * <p>
     * This builder provides a fluent interface for creating BizUser instances with
     * optional parameters, improving code readability and maintainability.
     */
    public static class BizUserBuilder {
        private Long id;
        private String username;
        private String email;
        private String firstName;
        private String lastName;
        private String phoneNumber;
        private Boolean active;
        private Instant createdAt;
        private Instant updatedAt;

        /**
         * Private constructor to enforce builder pattern usage.
         */
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

        public BizUserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public BizUserBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public BizUserBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public BizUserBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public BizUserBuilder active(Boolean active) {
            this.active = active;
            return this;
        }

        public BizUserBuilder createdAt(Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public BizUserBuilder updatedAt(Instant updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        /**
         * Builds and returns a new BizUser instance with the configured values.
         *
         * @return a new BizUser instance
         */
        public BizUser build() {
            return new BizUser(id, username, email, firstName, lastName, phoneNumber, active,
                    createdAt, updatedAt);
        }
    }
}