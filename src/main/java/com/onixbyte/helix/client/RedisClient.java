package com.onixbyte.helix.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Objects;

/**
 * A client class for interacting with Redis, providing simple and type-safe access to common Redis
 * operations such as setting, retrieving, incrementing, decrementing, and deleting keys.
 * <p>
 * This class abstracts the direct usage of {@link RedisTemplate} for value operations.
 *
 * @author zihluwang
 */
@Component
public class RedisClient {

    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * Constructs a new RedisClient with the specified {@link RedisTemplate}.
     *
     * @param redisTemplate the template used for Redis interaction
     */
    @Autowired
    public RedisClient(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * Set a value for a given key in Redis.
     *
     * @param key   the key to set
     * @param value the value associated with the key
     * @param <T>   the type of the value
     */
    public <T> void set(String key, T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * Set a value for a given key in Redis with a specified expiry timeout.
     *
     * @param key     the key to set
     * @param value   the value associated with the key
     * @param timeout the time after which the key should expire
     * @param <T>     the type of the value
     */
    public <T> void set(String key, T value, Duration timeout) {
        redisTemplate.opsForValue().set(key, value, timeout);
    }

    /**
     * Get the value associated with a given key from Redis.
     * <p>
     * The returned object is of type {@code Object} and may require manual casting.
     *
     * @param key the key to retrieve
     * @return the value associated with the key, or {@code null} if the key does not exist
     */
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * Get the value associated with a given key from Redis, attempting to cast it to the
     * specified type.
     *
     * @param key  the key to retrieve
     * @param type the class type to cast the retrieved value to
     * @param <T>  the type of the expected value
     * @return the value associated with the key, cast to type T, or {@code null} if the key does
     * not exist
     * @throws IllegalStateException if the retrieved value cannot be cast to the specified type
     */
    public <T> T get(String key, Class<T> type) {
        var value = redisTemplate.opsForValue().get(key);
        if (Objects.isNull(value)) {
            return null;
        }

        if (type.isInstance(value)) {
            return type.cast(value);
        }

        throw new IllegalStateException("Cannot cast " + value.getClass().getName() + " to " + type.getName());
    }

    /**
     * Increment the value of the key by one.
     * <p>
     * If the key does not exist, it is created and set to 0 before the increment operation. If the
     * value stored at the key is not an integer, an exception may be thrown by Redis.
     *
     * @param key the key to increment
     * @return the new value of the key after the increment
     */
    public Long increment(String key) {
        return redisTemplate.opsForValue().increment(key);
    }

    /**
     * Decrement the value of the key by one.
     * <p>
     * If the key does not exist, it is created and set to 0 before the decrement operation. If the
     * value stored at the key is not an integer, an exception may be thrown by Redis.
     *
     * @param key the key to decrement
     * @return the new value of the key after the decrement
     */
    public Long decrement(String key) {
        return redisTemplate.opsForValue().decrement(key);
    }

    /**
     * Delete a key from Redis.
     *
     * @param key the key to delete
     * @return {@code true} if the key was deleted, {@code false} if the key did not exist
     */
    public boolean delete(String key) {
        return redisTemplate.delete(key);
    }
}
