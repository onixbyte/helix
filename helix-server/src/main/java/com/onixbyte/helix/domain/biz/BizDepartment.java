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
 * Business object representing organisational departments within the Helix application.
 *
 * <p>This business object encapsulates department data for business logic operations, providing
 * a clean interface for department-related business operations within the service layer. It
 * serves as a data transfer object between different application layers whilst maintaining the
 * integrity of department information.
 *
 * <p>The BizDepartment contains all department information necessary for business operations,
 * including department identification, hierarchical structure information, and administrative
 * metadata. It supports organisational structure management and department hierarchy operations.
 *
 * <p>This object is designed to be used in business logic implementations, service layer
 * operations, and as a foundation for creating view objects or API responses that require
 * department information.
 *
 * @see com.onixbyte.helix.domain.entity.Department
 * @see NormalStatus
 * @author zihluwang
 * @since 1.0.0
 */
public class BizDepartment {

    /**
     * The unique identifier for this department.
     */
    private Long id;

    /**
     * The display name of this department.
     *
     * <p>This should be a human-readable name that clearly identifies the department, such as
     * "Human Resources", "Engineering", or "Sales and Marketing".
     */
    private String name;

    /**
     * The unique identifier of the parent department.
     *
     * <p>This field establishes the hierarchical relationship between departments. A null value
     * indicates this is a root-level department. This enables the creation of organisational
     * trees and nested department structures.
     */
    private Long parentId;

    /**
     * The sort order for this department.
     *
     * <p>Used to maintain consistent ordering when displaying departments in administrative
     * interfaces, organisational charts, or selection lists.
     */
    private Integer sort;

    /**
     * A detailed description of this department.
     *
     * <p>This field can contain information about the department's purpose, responsibilities,
     * and organisational role within the company.
     */
    private String description;

    /**
     * The current status of this department.
     *
     * <p>Determines whether this department is active and operational within the organisation.
     */
    private NormalStatus status;

    /**
     * The timestamp when this department was created.
     */
    private Instant createdAt;

    /**
     * The timestamp when this department was last updated.
     */
    private Instant updatedAt;

    /**
     * Default constructor for serialisation frameworks.
     */
    public BizDepartment() {
    }

    /**
     * Constructs a new BizDepartment with all fields specified.
     *
     * @param id          the unique identifier
     * @param name        the display name
     * @param parentId    the parent department identifier
     * @param sort        the sort order
     * @param description the department description
     * @param status      the department status
     * @param createdAt   the creation timestamp
     * @param updatedAt   the last update timestamp
     */
    public BizDepartment(Long id, String name, Long parentId, Integer sort, String description,
                         NormalStatus status, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.sort = sort;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * Gets the unique identifier for this department.
     *
     * @return the department ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier for this department.
     *
     * @param id the department ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the display name of this department.
     *
     * @return the department name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the display name of this department.
     *
     * @param name the department name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the unique identifier of the parent department.
     *
     * @return the parent department ID
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * Sets the unique identifier of the parent department.
     *
     * @param parentId the parent department ID to set
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * Gets the sort order for this department.
     *
     * @return the sort order
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * Sets the sort order for this department.
     *
     * @param sort the sort order to set
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * Gets the detailed description of this department.
     *
     * @return the department description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the detailed description of this department.
     *
     * @param description the department description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the current status of this department.
     *
     * @return the department status
     */
    public NormalStatus getStatus() {
        return status;
    }

    /**
     * Sets the current status of this department.
     *
     * @param status the department status to set
     */
    public void setStatus(NormalStatus status) {
        this.status = status;
    }

    /**
     * Gets the timestamp when this department was created.
     *
     * @return the creation timestamp
     */
    public Instant getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the timestamp when this department was created.
     *
     * @param createdAt the creation timestamp to set
     */
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets the timestamp when this department was last updated.
     *
     * @return the last update timestamp
     */
    public Instant getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets the timestamp when this department was last updated.
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
        BizDepartment that = (BizDepartment) obj;
        return Objects.equals(id, that.id) &&
               Objects.equals(name, that.name) &&
               Objects.equals(parentId, that.parentId) &&
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
        return Objects.hash(id, name, parentId, sort, description, status, createdAt, updatedAt);
    }

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return "BizDepartment{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", parentId=" + parentId +
               ", sort=" + sort +
               ", description='" + description + '\'' +
               ", status=" + status +
               ", createdAt=" + createdAt +
               ", updatedAt=" + updatedAt +
               '}';
    }

    /**
     * Creates a new Builder instance for constructing BizDepartment objects.
     *
     * @return a new Builder instance
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing BizDepartment instances with a fluent API.
     */
    public static class Builder {
        private Long id;
        private String name;
        private Long parentId;
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
         * Sets the unique identifier for the department.
         *
         * @param id the department ID
         * @return this Builder instance for method chaining
         */
        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        /**
         * Sets the display name of the department.
         *
         * @param name the department name
         * @return this Builder instance for method chaining
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets the parent department identifier.
         *
         * @param parentId the parent department ID
         * @return this Builder instance for method chaining
         */
        public Builder parentId(Long parentId) {
            this.parentId = parentId;
            return this;
        }

        /**
         * Sets the sort order for the department.
         *
         * @param sort the sort order
         * @return this Builder instance for method chaining
         */
        public Builder sort(Integer sort) {
            this.sort = sort;
            return this;
        }

        /**
         * Sets the detailed description of the department.
         *
         * @param description the department description
         * @return this Builder instance for method chaining
         */
        public Builder description(String description) {
            this.description = description;
            return this;
        }

        /**
         * Sets the current status of the department.
         *
         * @param status the department status
         * @return this Builder instance for method chaining
         */
        public Builder status(NormalStatus status) {
            this.status = status;
            return this;
        }

        /**
         * Sets the timestamp when the department was created.
         *
         * @param createdAt the creation timestamp
         * @return this Builder instance for method chaining
         */
        public Builder createdAt(Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        /**
         * Sets the timestamp when the department was last updated.
         *
         * @param updatedAt the last update timestamp
         * @return this Builder instance for method chaining
         */
        public Builder updatedAt(Instant updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        /**
         * Builds and returns a new BizDepartment instance with the configured properties.
         *
         * @return a new BizDepartment instance
         */
        public BizDepartment build() {
            return new BizDepartment(id, name, parentId, sort, description, status, createdAt,
                                     updatedAt);
        }
    }
}
