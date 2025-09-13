package com.onixbyte.helix.domain.biz;

import com.onixbyte.helix.domain.entity.Authority;
import com.onixbyte.helix.constant.Status;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Business object for Authority entity.
 * <p>
 * This class represents the business layer abstraction of an Authority, providing additional
 * business logic and validation capabilities beyond the basic entity representation.
 *
 * @author Zihlu Wang
 * @version 1.0.0
 * @since 1.0.0
 */
public class BizAuthority {

    private Long id;
    private String name;
    private String code;
    private String description;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /**
     * Default constructor.
     */
    public BizAuthority() {
    }

    /**
     * Constructs a BizAuthority with all fields.
     *
     * @param id          the authority ID
     * @param name        the authority name
     * @param code        the authority code
     * @param description the authority description
     * @param status      the authority status
     * @param createdAt   the creation timestamp
     * @param updatedAt   the last update timestamp
     */
    public BizAuthority(Long id, String name, String code, String description,
                        Status status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * Creates a BizAuthority from an Authority entity.
     *
     * @param authority the Authority entity
     * @return the BizAuthority instance
     */
    public static BizAuthority fromEntity(Authority authority) {
        if (authority == null) {
            return null;
        }
        return new BizAuthority(
                authority.getId(),
                authority.getName(),
                authority.getCode(),
                authority.getDescription(),
                authority.getStatus(),
                authority.getCreatedAt(),
                authority.getUpdatedAt()
        );
    }

    /**
     * Converts this BizAuthority to an Authority entity.
     *
     * @return the Authority entity
     */
    public Authority asEntity() {
        return Authority.builder()
                .id(this.id)
                .name(this.name)
                .code(this.code)
                .description(this.description)
                .status(this.status)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .build();
    }

    // Getters and Setters
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
        BizAuthority that = (BizAuthority) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) &&
                Objects.equals(code, that.code) && Objects.equals(description, that.description) &&
                status == that.status && Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code, description, status, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "BizAuthority{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    /**
     * Creates a new BizAuthorityBuilder instance.
     *
     * @return a new BizAuthorityBuilder
     */
    public static BizAuthorityBuilder builder() {
        return new BizAuthorityBuilder();
    }

    /**
     * Builder class for BizAuthority.
     */
    public static class BizAuthorityBuilder {
        private Long id;
        private String name;
        private String code;
        private String description;
        private Status status;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        private BizAuthorityBuilder() {
        }

        public BizAuthorityBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public BizAuthorityBuilder name(String name) {
            this.name = name;
            return this;
        }

        public BizAuthorityBuilder code(String code) {
            this.code = code;
            return this;
        }

        public BizAuthorityBuilder description(String description) {
            this.description = description;
            return this;
        }

        public BizAuthorityBuilder status(Status status) {
            this.status = status;
            return this;
        }

        public BizAuthorityBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public BizAuthorityBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        /**
         * Builds the BizAuthority instance.
         *
         * @return the constructed BizAuthority
         */
        public BizAuthority build() {
            return new BizAuthority(id, name, code, description, status, createdAt, updatedAt);
        }
    }
}