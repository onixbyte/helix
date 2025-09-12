package com.onixbyte.helix.domain.entity;

import com.onixbyte.helix.constant.NormalStatus;

import java.time.Instant;
import java.util.Objects;

/**
 * Entity representing security roles within the Helix application.
 * <p>
 * A role defines a collection of permissions and authorities that can be assigned to users within
 * the system. Roles are a fundamental component of the Role-Based Access Control (RBAC)
 * security model, allowing for granular permission management and access control.
 * <p>
 * Each role has a unique code for system identification, a descriptive name for display purposes,
 * and can be marked as a default role that is automatically assigned to new users
 * upon registration.
 * <p>
 * Roles can be associated with multiple authorities through the {@link RoleAuthority} entity,
 * creating a many-to-many relationship that defines the specific permissions granted to users
 * assigned to this role.
 * <p>
 * The sort field allows for consistent ordering of roles in administrative interfaces, whilst the
 * status field enables roles to be activated or deactivated without deletion.
 *
 * @author zihluwang
 * @see Authority
 * @see RoleAuthority
 * @see User
 * @see NormalStatus
 * @since 1.0.0
 */
public class Role {

    /**
     * The unique identifier for this role.
     */
    private Long id;

    /**
     * The display name of this role.
     * <p>
     * This should be a human-readable name that clearly describes the role's purpose, such as
     * "{@code Administrator}", "{@code Manager}", or "{@code Standard User}".
     */
    private String name;

    /**
     * The unique code identifier for this role.
     * <p>
     * This code is used for system identification and should be unique across all roles.
     * It's typically used in security checks and internal system references.
     */
    private String code;

    /**
     * The sort order for this role.
     * <p>
     * Used to maintain consistent ordering when displaying roles in administrative interfaces or
     * selection lists.
     */
    private Integer sort;

    /**
     * Indicates whether this role is assigned by default to new users.
     * <p>
     * When set to {@code true}, this role will be automatically assigned to users upon registration
     * or creation. Only one role should typically be marked as default.
     */
    private Boolean defaultValue;

    /**
     * A detailed description of this role.
     * <p>
     * This field can contain information about the role's purpose, responsibilities, and the types
     * of permissions it grants.
     */
    private String description;

    /**
     * The current status of this role.
     * <p>
     * Determines whether this role is active and available for assignment to users.
     */
    private NormalStatus status;

    /**
     * The timestamp when this role was created.
     */
    private Instant createdAt;

    /**
     * The timestamp when this role was last updated.
     */
    private Instant updatedAt;

    /**
     * Default constructor for JPA and serialisation frameworks.
     */
    public Role() {
    }

    /**
     * Constructs a new Role with all fields specified.
     *
     * @param id           the unique identifier
     * @param name         the role name
     * @param code         the unique role code
     * @param sort         the sort order for display purposes
     * @param defaultValue whether this role is assigned by default
     * @param description  the detailed description of the role
     * @param status       the current status
     * @param createdAt    the creation timestamp
     * @param updatedAt    the last update timestamp
     */
    public Role(Long id, String name, String code, Integer sort, Boolean defaultValue, String description, NormalStatus status, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.sort = sort;
        this.defaultValue = defaultValue;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Boolean getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Boolean defaultValue) {
        this.defaultValue = defaultValue;
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
        Role role = (Role) o;
        return Objects.equals(id, role.id) && Objects.equals(name, role.name) && Objects.equals(code, role.code) && Objects.equals(sort, role.sort) && Objects.equals(defaultValue, role.defaultValue) && Objects.equals(description, role.description) && status == role.status && Objects.equals(createdAt, role.createdAt) && Objects.equals(updatedAt, role.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code, sort, defaultValue, description, status, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", sort=" + sort +
                ", defaultValue=" + defaultValue +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    /**
     * Creates a new builder instance for constructing Role objects.
     *
     * @return a new {@link RoleBuilder} instance
     */
    public static RoleBuilder builder() {
        return new RoleBuilder();
    }

    /**
     * Builder class for constructing {@link Role} instances.
     * <p>
     * This builder provides a fluent interface for setting role properties and ensures consistent
     * object construction.
     */
    public static class RoleBuilder {
        private Long id;
        private String name;
        private String code;
        private Integer sort;
        private Boolean defaultValue;
        private String description;
        private NormalStatus status;
        private Instant createdAt;
        private Instant updatedAt;

        /**
         * Private constructor to enforce builder pattern usage.
         */
        private RoleBuilder() {
        }

        public RoleBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public RoleBuilder name(String name) {
            this.name = name;
            return this;
        }

        public RoleBuilder code(String code) {
            this.code = code;
            return this;
        }

        public RoleBuilder sort(Integer sort) {
            this.sort = sort;
            return this;
        }

        public RoleBuilder defaultValue(Boolean defaultValue) {
            this.defaultValue = defaultValue;
            return this;
        }

        public RoleBuilder description(String description) {
            this.description = description;
            return this;
        }

        public RoleBuilder status(NormalStatus status) {
            this.status = status;
            return this;
        }

        public RoleBuilder createdAt(Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public RoleBuilder updatedAt(Instant updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        /**
         * Builds and returns a new Role instance with the configured properties.
         *
         * @return a new {@link Role} instance
         */
        public Role build() {
            return new Role(id, name, code, sort, defaultValue, description, status, createdAt, updatedAt);
        }
    }
}
