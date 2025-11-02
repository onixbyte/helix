package com.onixbyte.helix.domain.entity;

import com.onixbyte.helix.exception.BizException;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

public class Setting {

    private Long id;
    private String name;
    private String description;
    private String value;
    private String defaultValue;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Setting() {
    }

    public Setting(Long id, String name, String description, String value, String defaultValue, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    public boolean asBoolean() {
        var val = fetchValueOrDefault();
        return Boolean.parseBoolean(val);
    }

    public Integer asInt() {
        try {
            var val = fetchValueOrDefault();
            return Integer.parseInt(val);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public Long asLong() {
        try {
            var val = fetchValueOrDefault();
            return Long.parseLong(val);
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
            return new Setting(id, name, description, value, defaultValue, createdAt, updatedAt);
        }
    }
}
