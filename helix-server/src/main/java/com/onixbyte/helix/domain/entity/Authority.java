package com.onixbyte.helix.domain.entity;

import com.onixbyte.helix.constant.Status;
import org.springframework.security.core.GrantedAuthority;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents an authority (permission) entity within the access control system.
 * <p>
 * Authorities define specific permissions or capabilities that can be granted to roles. They
 * represent the finest level of access control granularity, allowing for precise
 * permission management. Authorities are typically associated with specific actions or resources
 * within the application.
 *
 * @author zihluwang
 * @version 1.0
 * @since 1.0
 */
public class Authority {

    /**
     * The unique identifier for the authority.
     * <p>
     * This serves as the primary key in the database and is used for all internal references to the
     * authority entity.
     */
    private Long id;

    /**
     * The unique code identifier for the authority.
     * <p>
     * This field contains a system-level identifier that uniquely identifies the permission. It is
     * typically used in code for permission checks and should follow a consistent naming convention
     * (e.g., "USER_READ", "ADMIN_WRITE").
     */
    private String code;

    /**
     * The human-readable name of the authority.
     * <p>
     * This field contains the display name of the authority as it should appear in user interfaces
     * and administrative panels for permission management.
     */
    private String name;

    /**
     * A detailed description of what this authority grants.
     * <p>
     * This field provides additional context about what specific permissions or capabilities this
     * authority represents, helping administrators understand the implications of granting
     * this authority.
     */
    private String description;

    /**
     * The current status of the authority.
     * <p>
     * This field determines whether the authority is active, inactive, or in any other state as
     * defined by the {@link Status} enumeration.
     */
    private Status status;

    /**
     * The timestamp when this authority record was created.
     * <p>
     * This field is automatically set when the authority entity is first persisted and provides
     * audit information about when the authority was established.
     */
    private LocalDateTime createdAt;

    /**
     * The timestamp when this authority record was last updated.
     * <p>
     * This field is automatically updated whenever any changes are made to the authority entity and
     * provides audit information about the most recent modification.
     */
    private LocalDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    public Authority() {
    }

    public Authority(Long id, String code, String name, String description, Status status, LocalDateTime createdAt, LocalDateTime updatedAt) {
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
        Authority authority = (Authority) o;
        return Objects.equals(id, authority.id) && Objects.equals(code, authority.code) && Objects.equals(name, authority.name) && Objects.equals(description, authority.description) && status == authority.status && Objects.equals(createdAt, authority.createdAt) && Objects.equals(updatedAt, authority.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, name, description, status, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "Authority{" +
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
     * Creates a new Builder instance for constructing Authority objects.
     *
     * @return a new AuthorityBuilder instance
     */
    public static AuthorityBuilder builder() {
        return new AuthorityBuilder();
    }

    /**
     * Builder class for constructing Authority instances with a fluent API.
     * <p>
     * This builder provides a convenient way to construct Authority objects with optional parameters,
     * following the Builder pattern for improved readability and maintainability.
     */
    public static class AuthorityBuilder {
        private Long id;
        private String code;
        private String name;
        private String description;
        private Status status;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

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

        public AuthorityBuilder status(Status status) {
            this.status = status;
            return this;
        }

        public AuthorityBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public AuthorityBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        /**
         * Builds and returns a new Authority instance with the configured properties.
         *
         * @return a new Authority instance
         */
        public Authority build() {
            return new Authority(id, code, name, description, status, createdAt, updatedAt);
        }
    }

    public GrantedAuthority asGrantedAuthority() {
        return this::getCode;
    }
}
