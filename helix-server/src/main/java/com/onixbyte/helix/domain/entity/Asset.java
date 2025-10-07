package com.onixbyte.helix.domain.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity(name = "assets")
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "assets_id_seq_generator")
    @SequenceGenerator(
            name = "assets_id_seq_generator",
            sequenceName = "assets_id_seq",
            allocationSize = 1
    )
    private Long id;

    private String key;

    private Long uploadBy;

    private LocalDateTime uploadTime;

    public Asset() {
    }

    public Asset(Long id, String key, Long uploadBy, LocalDateTime uploadTime) {
        this.id = id;
        this.key = key;
        this.uploadBy = uploadBy;
        this.uploadTime = uploadTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Long getUploadBy() {
        return uploadBy;
    }

    public void setUploadBy(Long uploadBy) {
        this.uploadBy = uploadBy;
    }

    public LocalDateTime getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(LocalDateTime uploadTime) {
        this.uploadTime = uploadTime;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Asset that = (Asset) o;
        return Objects.equals(id, that.id) && Objects.equals(key, that.key) && Objects.equals(uploadBy, that.uploadBy) && Objects.equals(uploadTime, that.uploadTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, key, uploadBy, uploadTime);
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "id=" + id +
                ", key='" + key + '\'' +
                ", uploadBy=" + uploadBy +
                ", uploadTime=" + uploadTime +
                '}';
    }

    public static AssetBuilder builder() {
        return new AssetBuilder();
    }

    public static class AssetBuilder {
        private Long id;
        private String key;
        private Long uploadBy;
        private LocalDateTime uploadTime;

        private AssetBuilder() {
        }

        public AssetBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public AssetBuilder key(String key) {
            this.key = key;
            return this;
        }

        public AssetBuilder uploadBy(Long uploadBy) {
            this.uploadBy = uploadBy;
            return this;
        }

        public AssetBuilder uploadTime(LocalDateTime uploadTime) {
            this.uploadTime = uploadTime;
            return this;
        }

        public Asset build() {
            return new Asset(id, key, uploadBy, uploadTime);
        }
    }
}
