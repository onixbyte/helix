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

package com.onixbyte.helix.validation.executor;

import com.onixbyte.helix.validation.annotation.CustomValidation;
import com.onixbyte.helix.validation.validator.DynamicValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * CustomValidationExecutor is a dynamic validation executor that processes multiple validation
 * rules specified by the @CustomValidation annotation.
 *
 * @author siujamo
 */
@Component
public class CustomValidationExecutor
        implements ConstraintValidator<CustomValidation, Object>, ApplicationContextAware {

    private ApplicationContext applicationContext;
    private Class<? extends DynamicValidator<?>>[] validatorClasses;

    @Override
    public void initialize(CustomValidation constraintAnnotation) {
        this.validatorClasses = constraintAnnotation.rules();
    }

    /**
     * Validates the given value against all specified validators. If any validator fails,
     * the entire validation fails.
     *
     * @param value   the value to validate
     * @param context the constraint validator context for adding violation messages if needed
     * @return true if the value is valid according to all validators, false otherwise
     */
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (Objects.isNull(value)) return true;

        // Iterate through each specified validator class and perform validation
        for (Class<? extends DynamicValidator<?>> clazz : validatorClasses) {
            DynamicValidator<?> validator = getValidatorInstance(clazz);

            // Ensure the validator supports the type of the value being validated
            if (!validator.getSupportedType().isInstance(value)) {
                return false;
            }

            // Perform validation using the current validator
            if (!invokeValidator(validator, value, context)) {
                return false;
            }
        }

        return true; // All validators passed
    }

    /**
     * Invokes the validation logic of the given validator on the provided value.
     *
     * @param validator the dynamic validator to invoke
     * @param value     the value to validate
     * @param context   the constraint validator context for adding violation messages if needed
     * @return true if the value is valid according to the validator, false otherwise
     */
    private <T> boolean invokeValidator(DynamicValidator<T> validator, Object value, ConstraintValidatorContext context) {
        var supportedType = validator.getSupportedType();
        if (!supportedType.isInstance(value)) {
            return false;
        }
        return validator.isValid(supportedType.cast(value), context);
    }

    /**
     * Retrieves an instance of the specified DynamicValidator class from the Spring application context.
     *
     * @param clazz the class of the DynamicValidator to retrieve
     * @return an instance of the specified DynamicValidator
     * @throws IllegalStateException if the validator cannot be instantiated
     */
    private DynamicValidator<?> getValidatorInstance(Class<? extends DynamicValidator<?>> clazz) {
        try {
            return applicationContext.getBean(clazz);
        } catch (NoSuchBeanDefinitionException e) {
            throw new IllegalStateException("Failed to instantiate the validator: " + clazz.getName(), e);
        }
    }

    /**
     * Sets the ApplicationContext that this object runs in. This method is called by the
     * Spring framework during bean initialisation to provide the application context to
     * this validator.
     *
     * @param applicationContext the ApplicationContext object to be used by this validator
     */
    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}
