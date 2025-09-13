package com.onixbyte.helix.domain.view;

import com.onixbyte.helix.constant.NormalStatus;

import java.time.Instant;
import java.util.Objects;

/**
 * View object representing organisational positions for presentation layer operations.
 * <p>
 * This view object encapsulates position data optimised for display purposes, providing a clean
 * interface for position information presentation in user interfaces, API responses, and
 * client-side operations. It contains only the fields necessary for display and user interaction.
 * <p>
 * The PositionView is designed to be lightweight and focused on presentation concerns, excluding
 * internal system fields and sensitive information that should not be exposed to client
 * applications or end users.
 * <p>
 * This object is typically used in REST API responses, frontend data binding, employee management
 * interfaces, and any scenario where position information needs to be presented to users in
 * a clean, consumable format.
 *
 * @see com.onixbyte.helix.domain.entity.Position
 * @see com.onixbyte.helix.domain.biz.BizPosition
 * @see NormalStatus
 * @author zihluwang
 * @since 1.0.0
 */
public class PositionView {

    /**
     * The unique identifier for this position.
     */
    private Long id;

    /**
     * The display name of this position.
     * <p>
     * This is the human-readable name that will be displayed to users in interfaces,
     * employee profiles, and selection lists.
     */
    private String name;

    /**
     * The unique code identifier for this position.
     * <p>
     * This code may be used for client-side logic and position identification in user interfaces.
     */
    private String code;

    /**
     * A detailed description of this position.
     * <p>
     * This field provides users with information about the position's responsibilities,
     * requirements, and role within the organisation.
     */
    private String description;

    /**
     * The current status of this position.
     * <p>
     * Indicates whether this position is active and available for assignment or display
     * purposes.
     */
    private NormalStatus status;

    /**
     * The timestamp when this position was created.
     * <p>
     * This information may be displayed in administrative interfaces for audit and
     * tracking purposes.
     */
    private Instant createdAt;

    /**
     * Default constructor for serialisation frameworks.
     */
    public PositionView() {
    }

    /**
     * Constructs a new PositionView with all fields specified.
     *
     * @param id          the unique identifier
     * @param name        the display name
     * @param code        the unique code identifier
     * @param description the detailed description
     * @param status      the current status
     * @param createdAt   the creation timestamp
     */
    public PositionView(Long id, String name, String code, String description,
                        NormalStatus status, Instant createdAt) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
    }

    /**
     * Gets the unique identifier for this position.
     *
     * @return the position ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier for this position.
     *
     * @param id the position ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the display name of this position.
     *
     * @return the position name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the display name of this position.
     *
     * @param name the position name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the unique code identifier for this position.
     *
     * @return the position code
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the unique code identifier for this position.
     *
     * @param code the position code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets the detailed description of this position.
     *
     * @return the position description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the detailed description of this position.
     *
     * @param description the position description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the current status of this position.
     *
     * @return the position status
     */
    public NormalStatus getStatus() {
        return status;
    }

    /**
     * Sets the current status of this position.
     *
     * @param status the position status to set
     */
    public void setStatus(NormalStatus status) {
        this.status = status;
    }

    /**
     * Gets the timestamp when this position was created.
     *
     * @return the creation timestamp
     */
    public Instant getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the timestamp when this position was created.
     *
     * @param createdAt the creation timestamp to set
     */
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param obj the reference object with which to compare
     * @return true if this object is the same as the obj argument; false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        PositionView that = (PositionView) obj;
        return Objects.equals(id, that.id) &&
               Objects.equals(name, that.name) &&
               Objects.equals(code, that.code) &&
               Objects.equals(description, that.description) &&
               status == that.status &&
               Objects.equals(createdAt, that.createdAt);
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, code, description, status, createdAt);
    }

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return "PositionView{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", code='" + code + '\'' +
               ", description='" + description + '\'' +
               ", status=" + status +
               ", createdAt=" + createdAt +
               '}';
    }

    /**
     * Creates a new builder instance for constructing PositionView instances.
     *
     * @return a new PositionViewBuilder instance
     */
    public static PositionViewBuilder builder() {
        return new PositionViewBuilder();
    }

    /**
     * PositionViewBuilder class for constructing PositionView instances using the builder pattern.
     * <p>
     * This builder provides a fluent interface for creating PositionView instances with
     * optional parameters, improving code readability and maintainability.
     */
    public static class PositionViewBuilder {
        private Long id;
        private String name;
        private String code;
        private String description;
        private NormalStatus status;
        private Instant createdAt;

        /**
         * Private constructor to enforce builder pattern usage.
         */
        private PositionViewBuilder() {
        }

        /**
         * Sets the unique identifier for the position.
         *
         * @param id the position ID
         * @return this PositionViewBuilder instance for method chaining
         */
        public PositionViewBuilder id(Long id) {
            this.id = id;
            return this;
        }

        /**
         * Sets the display name of the position.
         *
         * @param name the position name
         * @return this PositionViewBuilder instance for method chaining
         */
        public PositionViewBuilder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets the unique code identifier for the position.
         *
         * @param code the position code
         * @return this PositionViewBuilder instance for method chaining
         */
        public PositionViewBuilder code(String code) {
            this.code = code;
            return this;
        }

        /**
         * Sets the detailed description of the position.
         *
         * @param description the position description
         * @return this PositionViewBuilder instance for method chaining
         */
        public PositionViewBuilder description(String description) {
            this.description = description;
            return this;
        }

        /**
         * Sets the current status of the position.
         *
         * @param status the position status
         * @return this PositionViewBuilder instance for method chaining
         */
        public PositionViewBuilder status(NormalStatus status) {
            this.status = status;
            return this;
        }

        /**
         * Sets the timestamp when the position was created.
         *
         * @param createdAt the creation timestamp
         * @return this PositionViewBuilder instance for method chaining
         */
        public PositionViewBuilder createdAt(Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        /**
         * Builds and returns a new PositionView instance with the configured values.
         *
         * @return a new PositionView instance
         */
        public PositionView build() {
            return new PositionView(id, name, code, description, status, createdAt);
        }
    }
}
