package com.onixbyte.helix.domain.entity;

import com.onixbyte.helix.enumeration.SettingType;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents a hot-deployable application setting, stored in the 'settings' database table.
 * <p>
 * This entity allows for dynamic configuration changes without application restarts.
 */
@Entity
@Table(name = "settings")
public class Setting {

    /**
     * Setting unique identifier, mapped to the primary key 'id'.
     * <p>
     * Uses BIGSERIAL (Long) and is set to auto-increment by the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Setting name. Used as a unique key for retrieval.
     */
    @Column(nullable = false, unique = true)
    private String name;

    /**
     * Setting description.
     */
    @Column
    private String description;

    /**
     * The type of the value (e.g., BOOLEAN, INT, STRING).
     * <p>
     * Mapped to the custom SQL type SETTING_TYPE, typically handled by JPA as an Enum.
     */
    @Column(nullable = false)
    @Enumerated
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private SettingType type;

    /**
     * Setting current value. Stored as a string regardless of the actual type.
     */
    @Column
    private String value;

    /**
     * Setting default value.
     */
    @Column(nullable = false)
    private String defaultValue;

    /**
     * The timestamp when this setting was created.
     * <p>
     * Set only on creation and remains unchanged.
     */
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * The timestamp when this setting was last updated.
     * <p>
     * Updated on every change to the entity.
     */
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Setting() {
    }

    public Setting(Long id, String name, String description, SettingType type, String value, String defaultValue, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.value = value;
        this.defaultValue = defaultValue;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SettingType getType() {
        return type;
    }

    public void setType(SettingType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
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

    public String fetchValueOrDefault() {
        if (Objects.nonNull(value) && !value.isBlank()) {
            return value;
        }
        return defaultValue;
    }

    public Boolean asBoolean() {
        var val = fetchValueOrDefault();
        if (type == SettingType.BOOLEAN) {
            return Boolean.parseBoolean(val);
        } else {
            return null;
        }
    }

    public Integer asInt() {
        try {
            var val = fetchValueOrDefault();
            if (type == SettingType.INT) {
                return Integer.parseInt(val);
            }
            return null;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public Long asLong() {
        try {
            var val = fetchValueOrDefault();
            if (type == SettingType.INT) {
                return Long.parseLong(val);
            }
            return null;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static SettingBuilder builder() {
        return new SettingBuilder();
    }

    public static class SettingBuilder {
        private Long id;
        private String name;
        private String description;
        private SettingType type;
        private String value;
        private String defaultValue;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        private SettingBuilder() {
        }

        public SettingBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public SettingBuilder name(String name) {
            this.name = name;
            return this;
        }

        public SettingBuilder description(String description) {
            this.description = description;
            return this;
        }

        public SettingBuilder type(SettingType type) {
            this.type = type;
            return this;
        }

        public SettingBuilder value(String value) {
            this.value = value;
            return this;
        }

        public SettingBuilder defaultValue(String defaultValue) {
            this.defaultValue = defaultValue;
            return this;
        }

        public SettingBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public SettingBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Setting build() {
            return new Setting(id, name, description, type, value, defaultValue, createdAt, updatedAt);
        }
    }
}