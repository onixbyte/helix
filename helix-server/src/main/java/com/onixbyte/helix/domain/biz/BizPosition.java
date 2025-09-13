/*
 * Copyright (C) 2024 OnixByte.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.onixbyte.helix.domain.biz;

import com.onixbyte.helix.constant.NormalStatus;

import java.time.Instant;
import java.util.Objects;

/**
 * Business object representing organisational positions within the Helix application.
 *
 * <p>This business object encapsulates position data for business logic operations, providing
 * a clean interface for position-related business operations within the service layer. It
 * serves as a data transfer object between different application layers whilst maintaining the
 * integrity of position information.
 *
 * <p>The BizPosition contains all position information necessary for business operations,
 * including position identification, display information, and administrative metadata. It
 * supports organisational structure management and position hierarchy operations.
 *
 * <p>This object is designed to be used in business logic implementations, service layer
 * operations, and as a foundation for creating view objects or API responses that require
 * position information.
 *
 * @see com.onixbyte.helix.domain.entity.Position
 * @see NormalStatus
 * @author zihluwang
 * @since 1.0.0
 */
public class BizPosition {

    /**
     * The unique identifier for this position.
     */
    private Long id;

    /**
     * The display name of this position.
     *
     * <p>This should be a human-readable name that clearly identifies the position, such as
     * "Software Engineer", "Project Manager", or "Senior Developer".
     */
    private String name;

    /**
     * The unique code identifier for this position.
     *
     * <p>This code is used for system identification and should be unique across all
     * positions. It's typically used in HR systems and internal position references.
     */
    private String code;

    /**
     * The sort order for this position.
     *
     * <p>Used to maintain consistent ordering when displaying positions in administrative
     * interfaces, organisational charts, or selection lists.
     */
    private Integer sort;

    /**
     * A detailed description of this position.
     *
     * <p>This field can contain information about the position's responsibilities,
     * requirements, and role within the organisation.
     */
    private String description;

    /**
     * The current status of this position.
     *
     * <p>Determines whether this position is active and available for assignment to
     * employees.
     */
    private NormalStatus status;

    /**
     * The timestamp when this position was created.
     */
    private Instant createdAt;

    /**
     * The timestamp when this position was last updated.
     */
    private Instant updatedAt;

    /**
     * Default constructor for serialisation frameworks.
     */
    public BizPosition() {
    }

    /**
     * Constructs a new BizPosition with all fields specified.
     *
     * @param id          the unique identifier
     * @param name        the display name
     * @param code        the unique code identifier
     * @param sort        the sort order
     * @param description the detailed description
     * @param status      the current status
     * @param createdAt   the creation timestamp
     * @param updatedAt   the last update timestamp
     */
    public BizPosition(Long id, String name, String code, Integer sort, String description,
                       NormalStatus status, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.sort = sort;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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
     * Gets the sort order for this position.
     *
     * @return the sort order
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * Sets the sort order for this position.
     *
     * @param sort the sort order to set
     */
    public void setSort(Integer sort) {
        this.sort = sort;
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
     * Gets the timestamp when this position was last updated.
     *
     * @return the last update timestamp
     */
    public Instant getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets the timestamp when this position was last updated.
     *
     * @param updatedAt the last update timestamp to set
     */
    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param obj the reference object with which to compare
     * @return true if this object is the same as the obj argument; false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BizPosition that = (BizPosition) obj;
        return Objects.equals(id, that.id) &&
               Objects.equals(name, that.name) &&
               Objects.equals(code, that.code) &&
               Objects.equals(sort, that.sort) &&
               Objects.equals(description, that.description) &&
               status == that.status &&
               Objects.equals(createdAt, that.createdAt) &&
               Objects.equals(updatedAt, that.updatedAt);
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, code, sort, description, status, createdAt, updatedAt);
    }

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return "BizPosition{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", code='" + code + '\'' +
               ", sort=" + sort +
               ", description='" + description + '\'' +
               ", status=" + status +
               ", createdAt=" + createdAt +
               ", updatedAt=" + updatedAt +
               '}';
    }

    /**
     * Creates a new Builder instance for constructing BizPosition objects.
     *
     * @return a new Builder instance
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing BizPosition instances with a fluent API.
     */
    public static class Builder {
        private Long id;
        private String name;
        private String code;
        private Integer sort;
        private String description;
        private NormalStatus status;
        private Instant createdAt;
        private Instant updatedAt;

        /**
         * Private constructor to enforce use of builder() method.
         */
        private Builder() {
        }

        /**
         * Sets the unique identifier for the position.
         *
         * @param id the position ID
         * @return this Builder instance for method chaining
         */
        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        /**
         * Sets the display name of the position.
         *
         * @param name the position name
         * @return this Builder instance for method chaining
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets the unique code identifier for the position.
         *
         * @param code the position code
         * @return this Builder instance for method chaining
         */
        public Builder code(String code) {
            this.code = code;
            return this;
        }

        /**
         * Sets the sort order for the position.
         *
         * @param sort the sort order
         * @return this Builder instance for method chaining
         */
        public Builder sort(Integer sort) {
            this.sort = sort;
            return this;
        }

        /**
         * Sets the detailed description of the position.
         *
         * @param description the position description
         * @return this Builder instance for method chaining
         */
        public Builder description(String description) {
            this.description = description;
            return this;
        }

        /**
         * Sets the current status of the position.
         *
         * @param status the position status
         * @return this Builder instance for method chaining
         */
        public Builder status(NormalStatus status) {
            this.status = status;
            return this;
        }

        /**
         * Sets the timestamp when the position was created.
         *
         * @param createdAt the creation timestamp
         * @return this Builder instance for method chaining
         */
        public Builder createdAt(Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        /**
         * Sets the timestamp when the position was last updated.
         *
         * @param updatedAt the last update timestamp
         * @return this Builder instance for method chaining
         */
        public Builder updatedAt(Instant updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        /**
         * Builds and returns a new BizPosition instance with the configured properties.
         *
         * @return a new BizPosition instance
         */
        public BizPosition build() {
            return new BizPosition(id, name, code, sort, description, status, createdAt,
                                   updatedAt);
        }
    }
}
