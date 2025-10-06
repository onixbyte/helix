package com.onixbyte.helix.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

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
 *
 * }</pre>
 *
 * @param endpoint        endpoint URL for the S3 service
 * @param publicHost      public host for access file anonymously
 * @param pathStyle       whether to enable path style for this s3 service
 * @param bucket          indicates which bucket is the target
 * @param region          region in which the S3 bucket is located
 * @param accessKeyId     access key ID used for authentication with the S3 provider
 * @param secretAccessKey secret access key used for authentication with the S3 provider
 * @author zihluwang
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = "app.file")
public record FileProperties(
        String endpoint,
        String publicHost,
        @DefaultValue("false") boolean pathStyle,
        String bucket,
        String region,
        String accessKeyId,
        String secretAccessKey
) {
}
