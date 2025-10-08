package com.onixbyte.helix.extension.redis.serializer;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

public class JacksonSerialiser {

    public static final GenericJackson2JsonRedisSerializer INSTANCE = initialiseSerializer();

    private static GenericJackson2JsonRedisSerializer initialiseSerializer() {
        var serializer = new GenericJackson2JsonRedisSerializer();

        serializer.configure((configurer) -> {
            configurer.registerModule(new JavaTimeModule());
            configurer.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        });

        return serializer;
    }
}
