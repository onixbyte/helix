package com.onixbyte.helix.validation.group;

/**
 * Validation group interface for update operations.
 * <p>
 * This marker interface is used with Bean Validation (JSR-303/JSR-380) to define a validation group
 * that is specifically applied during entity update operations. By using validation groups,
 * different validation rules can be applied depending on the context of the operation (update vs.
 * create vs. other scenarios).
 * <p>
 * <strong>Usage Pattern:</strong> This interface is typically used in conjunction with validation
 * annotations such as {@code @NotNull}, {@code @NotBlank}, {@code @Valid}, etc., to specify that
 * certain validation constraints should only be applied when updating existing entities.
 * <p>
 * <strong>Example Usage:</strong>
 * <pre>{@code
 * public class User {
 *     @NotNull(groups = OnUpdate.class, message = "ID is required for updates")
 *     @Positive(groups = OnUpdate.class)
 *     private Long id;
 *     
 *     @Size(min = 3, max = 50, groups = OnUpdate.class)
 *     private String username;
 *     
 *     // Password might be optional on update
 *     @Size(min = 8, groups = OnUpdate.class)
 *     private String password;
 *     
 *     // Other fields...
 * }
 * 
 * // In a REST controller:
 * @PutMapping("/users/{id}")
 * public ResponseEntity<User> updateUser(
 *     @PathVariable Long id,
 *     @Validated(OnUpdate.class) @RequestBody User user) {
 *     // Update user logic
 * }
 * }</pre>
 * <p>
 * <strong>Common Update Validation Scenarios:</strong>
 * <ul>
 * <li>
 *   <strong>ID Validation:</strong> Ensure entity ID is present and valid for updates
 * </li>
 * <li>
 *   <strong>Optional Fields:</strong> Allow certain fields to be optional during updates that were
 *   required during creation
 * </li>
 * <li>
 *   <strong>Partial Updates:</strong> Support PATCH operations where only modified fields
 *   need validation
 * </li>
 * <li>
 *   <strong>Version Control:</strong> Validate version fields for optimistic locking
 * </li>
 * </ul>
 * <p>
 * <strong>Benefits of Update-Specific Validation:</strong>
 * <ul>
 * <li>
 *   <strong>Flexible Field Requirements:</strong> Different fields may be required or optional
 *   compared to create operations
 * </li>
 * <li>
 *   <strong>Identity Validation:</strong> Ensure proper entity identification for update operations
 * </li>
 * <li>
 *   <strong>Conditional Logic:</strong> Apply validation rules specific to modification scenarios
 * </li>
 * </ul>
 * <p>
 * This interface contains no methods and serves purely as a marker for the Bean Validation
 * framework to identify which constraints should be applied during update operations.
 *
 * @author zihluwang
 * @since 1.0.0
 * @see OnCreate
 * @see jakarta.validation.groups.Default
 * @see org.springframework.validation.annotation.Validated
 * @see <a href="https://beanvalidation.org/2.0/spec/#constraintdeclarationvalidationprocess-groupsequence">Bean Validation Specification - Validation Groups</a>
 */
public interface OnUpdate {
}
