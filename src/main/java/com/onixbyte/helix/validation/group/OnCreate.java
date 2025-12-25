package com.onixbyte.helix.validation.group;

/**
 * Validation group interface for create operations.
 * <p>
 * This marker interface is used with Bean Validation (JSR-303/JSR-380) to define a validation group
 * that is specifically applied during entity creation operations. By using validation groups,
 * different validation rules can be applied depending on the context of the operation (create vs.
 * update vs. other scenarios).
 * <p>
 * <strong>Usage Pattern:</strong> This interface is typically used in conjunction with validation
 * annotations such as {@code @NotNull}, {@code @NotBlank}, {@code @Valid}, etc., to specify that
 * certain validation constraints should only be applied when creating new entities.
 * <p>
 * <strong>Example Usage:</strong>
 * <pre>{@code
 * public class User {
 *     @NotNull(groups = OnCreate.class, message = "Username is required for new users")
 *     @Size(min = 3, max = 50, groups = OnCreate.class)
 *     private String username;
 *     
 *     @NotNull(groups = OnCreate.class, message = "Password is required for new users")
 *     @Size(min = 8, groups = OnCreate.class)
 *     private String password;
 *     
 *     // Other fields...
 * }
 * 
 * // In a REST controller:
 * @PostMapping("/users")
 * public ResponseEntity<User> createUser(
 *     @Validated(OnCreate.class) @RequestBody User user) {
 *     // Create user logic
 * }
 * }</pre>
 * <p>
 * <strong>Benefits of Validation Groups:</strong>
 * <ul>
 * <li>
 *   <strong>Context-Specific Validation:</strong> Apply different validation rules for different
 *   operations (e.g., password required on create but not on update)
 * </li>
 * <li>
 *   <strong>Flexible Constraint Application:</strong> Enable or disable specific validations based
 *   on the operation context
 * </li>
 * <li>
 *   <strong>Cleaner Code:</strong> Avoid complex conditional validation logic by using declarative
 *   group-based validation
 * </li>
 * </ul>
 * <p>
 * This interface contains no methods and serves purely as a marker for the Bean Validation
 * framework to identify which constraints should be applied during create operations.
 *
 * @author zihluwang
 * @since 1.0.0
 * @see OnUpdate
 * @see jakarta.validation.groups.Default
 * @see org.springframework.validation.annotation.Validated
 * @see <a href="https://beanvalidation.org/2.0/spec/#constraintdeclarationvalidationprocess-groupsequence">Bean Validation Specification - Validation Groups</a>
 */
public interface OnCreate {
}
