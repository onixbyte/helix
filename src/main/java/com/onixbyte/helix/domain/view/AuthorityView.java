package com.onixbyte.helix.domain.view;

import com.onixbyte.helix.constant.Status;
import com.onixbyte.helix.domain.entity.Authority;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * View object for Authority entity.
 * <p>
 * This class represents the view layer abstraction of an Authority, providing a simplified
 * representation suitable for presentation in user interfaces and API responses.
 *
 * @author Zihlu Wang
 * @version 1.0.0
 * @since 1.0.0
 */
public class AuthorityView {

    /**
     * The unique identifier for the authority.
     */
    private Long id;

    /**
     * The unique code identifier for the authority.
     */
    private String code;

    /**
     * The human-readable name of the authority.
     */
    private String name;

    /**
     * A detailed description of what this authority grants.
     */
    private String description;

    /**
     * The current status of the authority.
     */
    private Status status;

    /**
     * The timestamp when this authority record was created.
     */
    private LocalDateTime createdAt;

    /**
     * The timestamp when this authority record was last updated.
     */
    private LocalDateTime updatedAt;

    /**
     * Default constructor for serialisation frameworks.
     */
    public AuthorityView() {
    }

    /**
     * Constructs a new AuthorityView with all fields specified.
     *
     * @param id          the unique identifier
     * @param code        the unique code identifier
     * @param name        the human-readable name
     * @param description the detailed description
     * @param status      the current status
     * @param createdAt   the creation timestamp
     * @param updatedAt   the last update timestamp
     */
    public AuthorityView(Long id, String code, String name, String description, Status status,
                        LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * Creates an AuthorityView from an Authority entity.
     *
     * @param entity the Authority entity
     * @return a new AuthorityView instance
     */
    public static AuthorityView fromEntity(Authority entity) {
        if (entity == null) {
            return null;
        }
        return new AuthorityView(
                entity.getId(),
                entity.getCode(),
                entity.getName(),
                entity.getDescription(),
                entity.getStatus(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorityView that = (AuthorityView) o;
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
        return "AuthorityView{" +
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
     * Returns a builder for constructing AuthorityView instances.
     *
     * @return a new AuthorityViewBuilder instance
     */
    public static AuthorityViewBuilder builder() {
        return new AuthorityViewBuilder();
    }

    /**
     * Builder class for constructing {@link AuthorityView} instances.
     * <p>
     * This builder provides a fluent interface for creating AuthorityView objects with optional
     * parameters, following the builder pattern for improved readability and maintainability.
     */
    public static class AuthorityViewBuilder {
        private Long id;
        private String code;
        private String name;
        private String description;
        private Status status;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        private AuthorityViewBuilder() {
        }

        public AuthorityViewBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public AuthorityViewBuilder code(String code) {
            this.code = code;
            return this;
        }

        public AuthorityViewBuilder name(String name) {
            this.name = name;
            return this;
        }

        public AuthorityViewBuilder description(String description) {
            this.description = description;
            return this;
        }

        public AuthorityViewBuilder status(Status status) {
            this.status = status;
            return this;
        }

        public AuthorityViewBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public AuthorityViewBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        /**
         * Builds and returns a new AuthorityView instance with the configured parameters.
         *
         * @return a new AuthorityView instance
         */
        public AuthorityView build() {
            return new AuthorityView(id, code, name, description, status, createdAt, updatedAt);
        }
    }
}