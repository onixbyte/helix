package com.onixbyte.helix.common.redis.serialisers;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.onixbyte.helix.common.jackson.JacksonModules;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

/**
 * Commonly used jackson serialiser for redis.
 *
 * @author zihluwang
 */
public class JacksonSerialiser {

    /**
     * Generic jackson redis serialiser.
     */
    public static final GenericJackson2JsonRedisSerializer INSTANCE = initialiseSerializer();

    private static GenericJackson2JsonRedisSerializer initialiseSerializer() {
        var serializer = new GenericJackson2JsonRedisSerializer();

        serializer.configure((configurer) -> {
            configurer.registerModule(JacksonModules.JAVA_TIME_MODULE);
            configurer.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        });

        return serializer;
    }
}
