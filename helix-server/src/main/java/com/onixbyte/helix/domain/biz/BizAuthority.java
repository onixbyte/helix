package com.onixbyte.helix.domain.biz;

import com.onixbyte.helix.constant.NormalStatus;

import java.time.Instant;
import java.util.Objects;

/**
 * Business object representing security authorities within the Helix application.
 * <p>
 * This business object encapsulates authority data for business logic operations, providing a clean
 * interface for permission-related business operations within the service layer. It serves as a
 * data transfer object between different application layers whilst maintaining the integrity of
 * authority information.
 * <p>
 * The BizAuthority contains all authority information necessary for business operations,
 * including authority identification, display information, and administrative metadata. It supports
 * role-based access control (RBAC) operations and permission management functionality.
 * <p>
 * This object is designed to be used in business logic implementations, service layer operations,
 * and as a foundation for creating view objects or API responses that require
 * authority information.
 *
 * @author zihluwang
 * @see com.onixbyte.helix.domain.entity.Authority
 * @see NormalStatus
 * @since 1.0.0
 */
public class BizAuthority {

    /**
     * The unique identifier for this authority.
     */
    private Long id;

    /**
     * The unique code identifier for this authority.
     * <p>
     * This code is used for system identification and should be unique across all authorities.
     * It's typically used in security checks and internal system references.
     */
    private String code;

    /**
     * The display name of this authority.
     * <p>
     * This should be a human-readable name that clearly describes the authority's purpose, such as
     * "User Management", "Report Access", or "System Configuration".
     */
    private String name;

    /**
     * The sort order for this authority.
     * <p>
     * Used to maintain consistent ordering when displaying authorities in administrative interfaces
     * or selection lists.
     */
    private Integer sort;

    /**
     * A detailed description of this authority.
     * <p>
     * This field can contain information about the authority's purpose, the specific permissions
     * it grants, and any usage guidelines.
     */
    private String description;

    /**
     * The current status of this authority.
     * <p>
     * Determines whether this authority is active and available for assignment to roles.
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
     * Default constructor for serialisation frameworks.
     */
    public BizAuthority() {
    }

    /**
     * Constructs a new BizAuthority with all fields specified.
     *
     * @param id          the unique identifier
     * @param code        the unique code identifier
     * @param name        the display name
     * @param sort        the sort order
     * @param description the detailed description
     * @param status      the current status
     * @param createdAt   the creation timestamp
     * @param updatedAt   the last update timestamp
     */
    public BizAuthority(Long id, String code, String name, Integer sort, String description, NormalStatus status, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.sort = sort;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public NormalStatus getStatus() {
        return status;
    }

    public void setStatus(NormalStatus status) {
        this.status = status;
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
        BizAuthority that = (BizAuthority) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(code, that.code) &&
                Objects.equals(name, that.name) &&
                Objects.equals(sort, that.sort) &&
                Objects.equals(description, that.description) &&
                status == that.status &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, name, sort, description, status, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "BizAuthority{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", sort=" + sort +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    /**
     * Creates a new builder instance for constructing BizAuthority instances.
     *
     * @return a new BizAuthorityBuilder instance
     */
    public static BizAuthorityBuilder builder() {
        return new BizAuthorityBuilder();
    }

    /**
     * Builder class for constructing BizAuthority instances using the builder pattern.
     * <p>
     * This builder provides a fluent interface for creating BizAuthority instances with
     * optional parameters, improving code readability and maintainability.
     */
    public static class BizAuthorityBuilder {
        private Long id;
        private String code;
        private String name;
        private Integer sort;
        private String description;
        private NormalStatus status;
        private Instant createdAt;
        private Instant updatedAt;

        /**
         * Private constructor to enforce builder pattern usage.
         */
        private BizAuthorityBuilder() {
        }

        public BizAuthorityBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public BizAuthorityBuilder code(String code) {
            this.code = code;
            return this;
        }

        public BizAuthorityBuilder name(String name) {
            this.name = name;
            return this;
        }

        public BizAuthorityBuilder sort(Integer sort) {
            this.sort = sort;
            return this;
        }

        public BizAuthorityBuilder description(String description) {
            this.description = description;
            return this;
        }

        public BizAuthorityBuilder status(NormalStatus status) {
            this.status = status;
            return this;
        }

        public BizAuthorityBuilder createdAt(Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public BizAuthorityBuilder updatedAt(Instant updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        /**
         * Builds and returns a new BizAuthority instance with the configured values.
         *
         * @return a new BizAuthority instance
         */
        public BizAuthority build() {
            return new BizAuthority(id, code, name, sort, description, status, createdAt, updatedAt);
        }
    }
}