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

package com.onixbyte.helix.validation.validator;

import jakarta.validation.ConstraintValidatorContext;

/**
 * DynamicValidator is an interface for implementing dynamic validation logic that can be applied
 * to various types of data.
 *
 * @param <T>
 * @author siujamo
 */
public interface DynamicValidator<T> {

    /**
     * Get the supported type of this validator.
     *
     * @return the supported type class
     */
    Class<T> getSupportedType();

    /**
     * Validate the given value and return whether it is valid according to the implemented logic.
     *
     * @param value   the value to validate
     * @param context the constraint validator context for adding violation messages if needed
     * @return true if the value is valid, false otherwise
     */
    boolean isValid(T value, ConstraintValidatorContext context);

    /**
     * A helper method to add a violation message to the context.
     *
     * @param context the constraint validator context
     * @param message the violation message to add
     * @return the updated constraint validator context with the violation message added
     */
    default ConstraintValidatorContext addViolationMessage(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        return context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation();
    }
}
