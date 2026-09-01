package com.onixbyte.helix.config;

import com.onixbyte.helix.properties.AssetProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

/**
 * Configuration class for asset storage services.
 * <p>
 * Enables configuration properties for S3 file storage services. Individual service beans are
 * created by their respective service classes to better support conditional configuration.
 *
 * @author zihluwang
 * @since 1.0.0
 */
@Configuration
@EnableConfigurationProperties({AssetProperties.class})
public class AssetConfig {

    /**
     * S3Client to store assets into S3 service.
     *
     * @param assetProperties asset properties
     * @return an S3 Client reference to custom S3 configuration properties
     */
    @Bean
    public S3Client s3Client(AssetProperties assetProperties) {
        // initialise AWS credentials
        var credentials = AwsBasicCredentials.create(
                assetProperties.accessKeyId(),
                assetProperties.secretAccessKey()
        );

        // prepare s3 client
        var s3ClientBuilder = S3Client.builder()
                .region(Region.of(assetProperties.region()))
                .credentialsProvider(StaticCredentialsProvider.create(credentials));

        // override endpoint
        Optional.ofNullable(assetProperties.endpoint())
                .ifPresent((endpoint) -> {
                    try {
                        s3ClientBuilder.endpointOverride(new URI(endpoint));
                    } catch (URISyntaxException e) {
                        throw new IllegalArgumentException("Endpoint is not valid.");
                    }
                });

        // set path style
        s3ClientBuilder.serviceConfiguration(S3Configuration.builder()
                .pathStyleAccessEnabled(assetProperties.pathStyle())
                .build()
        );

        return s3ClientBuilder.build();
    }
}
