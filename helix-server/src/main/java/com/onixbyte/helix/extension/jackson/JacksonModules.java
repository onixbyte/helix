package com.onixbyte.helix.extension.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.YearMonthDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.YearMonthSerializer;
import com.onixbyte.helix.common.datetime.Formatters;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;

/**
 * Commonly used Jackson modules.
 *
 * @author zihluwang
 */
public class JacksonModules {

    /**
     * App-level jackson module for java time supports.
     */
    public static final SimpleModule JAVA_TIME_MODULE = initialiseJavaTimeModule();

    /**
     * Initialise module to add support to jackson for Java Time.
     *
     * @return jackson time module
     */
    private static SimpleModule initialiseJavaTimeModule() {
        var module = new JavaTimeModule();
        module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(Formatters.DATE_TIME_FORMATTER));
        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(Formatters.DATE_TIME_FORMATTER));

        module.addDeserializer(LocalDate.class, new LocalDateDeserializer(Formatters.DATE_FORMATTER));
        module.addSerializer(LocalDate.class, new LocalDateSerializer(Formatters.DATE_FORMATTER));

        module.addDeserializer(LocalTime.class, new LocalTimeDeserializer(Formatters.TIME_FORMATTER));
        module.addSerializer(LocalTime.class, new LocalTimeSerializer(Formatters.TIME_FORMATTER));

        module.addDeserializer(YearMonth.class, new YearMonthDeserializer(Formatters.YEAR_MONTH_FORMATTER));
        module.addSerializer(YearMonth.class, new YearMonthSerializer(Formatters.YEAR_MONTH_FORMATTER));
        return module;
    }
}
