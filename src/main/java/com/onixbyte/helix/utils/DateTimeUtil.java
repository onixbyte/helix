package com.onixbyte.helix.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * DateTimeUtil helps to convert date and time between different types.
 *
 * @author zihluwang
 */
public class DateTimeUtil {

    /**
     * Convert {@link LocalDateTime} to {@link Instant}.
     *
     * @param localDateTime local date time to be converted
     * @return converted instant
     */
    public static Instant asInstant(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault())
                .toInstant();
    }
}
