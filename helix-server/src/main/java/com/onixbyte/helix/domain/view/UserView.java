package com.onixbyte.helix.domain.view;

import java.time.Instant;
import java.util.Objects;

/**
 * View object representing user information for presentation layer operations.
 * <p>
 * This view object encapsulates user data optimised for frontend display and user
 * interface operations. It provides a clean, presentation-focused interface for user information
 * whilst maintaining appropriate data visibility for client-side operations.
 * <p>
 * The UserView contains information suitable for display in user interfaces, administrative panels,
 * and API responses. It focuses on providing the essential data required for user management
 * interfaces whilst excluding sensitive information such as passwords or internal system details.
 * <p>
 * This object is designed to be serialised for API responses, used in frontend applications, and
 * serves as the primary data structure for user-related user interface components.
 *
 * @author zihluwang
 * @see com.onixbyte.helix.domain.entity.User
 * @see com.onixbyte.helix.domain.biz.BizUser
 * @since 1.0.0
 */
public class UserView {

    /**
     * The unique identifier for the user.
     * <p>
     * This field serves as the primary key for user identification in frontend applications and
     * API responses, enabling proper user reference and relationship management.
     */
    private Long id;

    /**
     * The unique username for the user account.
     * <p>
     * This field represents the user's chosen identifier for display purposes in user interfaces,
     * providing a human-readable reference for the user account.
     */
    private String username;

    /**
     * The user's email address.
     * <p>
     * This field displays the user's primary email address in user interfaces, useful for contact
     * information and account management purposes.
     */
    private String email;

    /**
     * The user's first name.
     * <p>
     * This field contains the user's given name for personalisation and display purposes throughout
     * the application interface.
     */
    private String firstName;

    /**
     * The user's last name.
     * <p>
     * This field contains the user's family name, used in conjunction with the first name for
     * complete user identification in user interfaces.
     */
    private String lastName;

    /**
     * The user's phone number.
     * <p>
     * This field displays the user's contact phone number in user interfaces, useful for contact
     * information and account management purposes.
     */
    private String phoneNumber;

    /**
     * Indicates whether the user account is currently active.
     * <p>
     * This field provides visibility into user account status for administrative interfaces,
     * enabling proper user management and access control display.
     */
    private Boolean active;

    /**
     * The timestamp when the user account was created.
     * <p>
     * This field provides account creation information for display in user interfaces, particularly
     * useful in administrative panels and user profile sections.
     */
    private Instant createdAt;

    /**
     * The timestamp when the user account was last updated.
     * <p>
     * This field shows when user information was last modified, useful for displaying account
     * activity and change tracking in user interfaces.
     */
    private Instant updatedAt;

    /**
     * Default constructor for serialisation frameworks.
     */
    public UserView() {
    }

    /**
     * Constructs a new UserView with all fields specified.
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
    public UserView(Long id, String username, String email, String firstName, String lastName,
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
        UserView userView = (UserView) o;
        return Objects.equals(id, userView.id) &&
                Objects.equals(username, userView.username) &&
                Objects.equals(email, userView.email) &&
                Objects.equals(firstName, userView.firstName) &&
                Objects.equals(lastName, userView.lastName) &&
                Objects.equals(phoneNumber, userView.phoneNumber) &&
                Objects.equals(active, userView.active) &&
                Objects.equals(createdAt, userView.createdAt) &&
                Objects.equals(updatedAt, userView.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, firstName, lastName, phoneNumber, active,
                createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "UserView{" +
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
     * Creates a new builder instance for constructing UserView instances.
     *
     * @return a new UserViewBuilder instance
     */
    public static UserViewBuilder builder() {
        return new UserViewBuilder();
    }

    /**
     * Builder class for constructing UserView instances using the builder pattern.
     * <p>
     * This builder provides a fluent interface for creating UserView instances with
     * optional parameters, improving code readability and maintainability.
     */
    public static class UserViewBuilder {
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

        public UserViewBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserViewBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserViewBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserViewBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public UserViewBuilder active(Boolean active) {
            this.active = active;
            return this;
        }

        public UserViewBuilder createdAt(Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public UserViewBuilder updatedAt(Instant updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        /**
         * Builds and returns a new UserView instance with the configured values.
         *
         * @return a new UserView instance
         */
        public UserView build() {
            return new UserView(id, username, email, firstName, lastName, phoneNumber, active,
                    createdAt, updatedAt);
        }
    }
}