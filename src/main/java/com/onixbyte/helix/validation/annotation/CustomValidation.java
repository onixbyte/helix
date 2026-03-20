/*
 * Copyright (c) 2024-2026 OnixByte
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.onixbyte.helix.validation.annotation;

import com.onixbyte.helix.validation.executor.CustomValidationExecutor;
import com.onixbyte.helix.validation.validator.DynamicValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * CustomValidation is a custom validation annotation that allows for dynamic validation rules to be
 * applied to fields or classes. It accepts an array of DynamicValidator classes that will be
 * executed to validate the annotated element.
 *
 * @author siujamo
 */
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CustomValidationExecutor.class)
public @interface CustomValidation {
    /**
     * An array of DynamicValidator classes that will be executed to validate the annotated element.
     *
     * @return an array of DynamicValidator classes
     */
    Class<? extends DynamicValidator<?>>[] rules();

    /**
     * The default validation message that will be used if any of the specified validators fail.
     *
     * @return the default validation message
     */
    String message() default "Validation failed for the annotated element.";

    /**
     * Groups can be used to specify different validation scenarios. This allows for more flexible
     * validation logic based on the context of the operation (e.g., creation vs. update).
     *
     * @return an array of validation groups
     */
    Class<?>[] groups() default {};

    /**
     * Payload can be used by clients of the Bean Validation API to assign custom payload objects
     * to a constraint. This is not commonly used but can be helpful for advanced validation
     * scenarios.
     *
     * @return an array of payload classes
     */
    Class<? extends Payload>[] payload() default {};
}
