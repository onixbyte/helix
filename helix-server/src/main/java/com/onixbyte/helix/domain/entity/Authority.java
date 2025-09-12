package com.onixbyte.helix.domain.entity;

import com.onixbyte.helix.constant.NormalStatus;

import java.time.Instant;
import java.util.Objects;

/**
 * Entity representing system authorities (permissions) within the Helix application.
 * <p>
 * An authority defines a specific permission or privilege that can be granted to users or roles
 * within the system. Authorities are used to control access to various system resources, features,
 * and operations through role-based access control (RBAC).
 * <p>
 * Each authority has a unique code identifier, a human-readable name, and an optional description
 * explaining its purpose. The status field indicates whether the authority is currently active and
 * available for assignment.
 * <p>
 * This entity supports the builder pattern for convenient object construction and includes standard
 * object methods for equality comparison and string representation.
 *
 * @author zihluwang
 * @see Role
 * @see RoleAuthority
 * @see NormalStatus
 * @since 1.0.0
 */
public class Authority {

    /**
     * The unique identifier for this authority.
     */
    private Long id;

    /**
     * The unique code identifier for this authority.
     * <p>
     * This code is used programmatically to reference the authority and should follow a consistent
     * naming convention (e.g., "{@code USER_READ}", "{@code ADMIN_WRITE}").
     */
    private String code;

    /**
     * The human-readable name of this authority.
     * <p>
     * This name is typically displayed in user interfaces and should be descriptive enough for
     * administrators to understand the authority's purpose.
     */
    private String name;

    /**
     * An optional detailed description of this authority.
     * <p>
     * This field provides additional context about what the authority grants access to and when it
     * should be used.
     */
    private String description;

    /**
     * The current status of this authority.
     * <p>
     * Determines whether this authority is active and available for assignment to roles and users.
     */
    private NormalStatus status;

    /**
     * The timestamp when this authority was created.
     */
    private Instant createdAt;

    /**
     * The timestamp when this authority was last updated.
     */
    private Instant updatedAt;

    /**
     * Default constructor for JPA and serialization frameworks.
     */
    public Authority() {
    }

    /**
     * Constructs a new Authority with all fields specified.
     *
     * @param id          the unique identifier
     * @param code        the unique code identifier
     * @param name        the human-readable name
     * @param description the detailed description (may be null)
     * @param status      the current status
     * @param createdAt   the creation timestamp
     * @param updatedAt   the last update timestamp
     */
    public Authority(Long id, String code, String name, String description, NormalStatus status, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Authority that = (Authority) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(code, that.code) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                status == that.status &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, name, description, status, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    /**
     * Creates a new builder instance for constructing Authority objects.
     *
     * @return a new {@link AuthorityBuilder} instance
     */
    public static AuthorityBuilder builder() {
        return new AuthorityBuilder();
    }

    /**
     * Builder class for constructing {@link Authority} instances.
     * <p>
     * This builder provides a fluent interface for setting authority properties and ensures
     * consistent object construction.
     */
    public static class AuthorityBuilder {
        private Long id;
        private String code;
        private String name;
        private String description;
        private NormalStatus status;
        private Instant createdAt;
        private Instant updatedAt;

        /**
         * Private constructor to enforce builder pattern usage.
         */
        private AuthorityBuilder() {
        }

        public AuthorityBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public AuthorityBuilder code(String code) {
            this.code = code;
            return this;
        }

        public AuthorityBuilder name(String name) {
            this.name = name;
            return this;
        }

        public AuthorityBuilder description(String description) {
            this.description = description;
            return this;
        }

        public AuthorityBuilder status(NormalStatus status) {
            this.status = status;
            return this;
        }

        public AuthorityBuilder createdAt(Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public AuthorityBuilder updatedAt(Instant updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        /**
         * Builds and returns a new Authority instance with the configured properties.
         *
         * @return a new {@link Authority} instance
         */
        public Authority build() {
            return new Authority(id, code, name, description, status, createdAt, updatedAt);
        }
    }
}
