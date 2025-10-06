package com.onixbyte.helix.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for S3-compatible file storage services.
 * <p>
 * This class encapsulates the essential settings required to connect to and utilise S3-based file
 * storage providers. It supports custom endpoints, regions, and credentials, allowing integration
 * with AWS S3 or compatible services.<br>
 * <p>
 * The configuration is intentionally minimal, with sensible defaults for common providers. Use this
 * class to enable S3 storage, specify connection details, and select the provider as appropriate.
 * <p>
 * Example usage in <code>application.yml</code>:
 * <pre>{@code
 * app:
 *   file:
 *     s3:
 *       enabled: true
 *       endpoint: "https://s3.eu-west-2.amazonaws.com"
 *       region: "eu-west-2"
 *       accessKeyId: "your-access-key-id"
 *       secretAccessKey: "your-secret-access-key"
 *       provider: "aws"
 * }</pre>
 *
 * @author zihluwang
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = "app.file")
public class FileProperties {

    /**
     * Indicates whether S3 file storage is enabled.
     * <p>
     * Set to {@code true} to activate S3-based file storage functionality.
     */
    private boolean enabled = false;

    /**
     * The endpoint URL for the S3 service.
     * <p>
     * This may be a custom endpoint for non-AWS providers.
     */
    private String endpoint;

    /**
     * The public host for accessing files anonymously.
     */
    private String publicHost;

    /**
     * Whether to enable path style for this s3 service.
     * <p>
     * While path style is enabled: {@code <public_host>/<bucket>/<file_key>}.
     * <p>
     * While path style is disabled: {@code <public_host>/<file_key>}.
     */
    private boolean pathStyle;

    /**
     * Name of the file bucket.
     */
    private String bucket;

    /**
     * The region in which the S3 bucket is located.
     * <p>
     * For AWS, this should match the bucket's region.
     */
    private String region;

    /**
     * The access key ID used for authentication with the S3 provider.
     */
    private String accessKeyId;

    /**
     * The secret access key used for authentication with the S3 provider.
     */
    private String secretAccessKey;

    public FileProperties() {
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getPublicHost() {
        return publicHost;
    }

    public void setPublicHost(String publicHost) {
        this.publicHost = publicHost;
    }

    public boolean isPathStyle() {
        return pathStyle;
    }

    public void setPathStyle(boolean pathStyle) {
        this.pathStyle = pathStyle;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getSecretAccessKey() {
        return secretAccessKey;
    }

    public void setSecretAccessKey(String secretAccessKey) {
        this.secretAccessKey = secretAccessKey;
    }
}
