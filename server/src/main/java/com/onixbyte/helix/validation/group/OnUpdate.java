package com.onixbyte.helix.validation.group;

/**
 * OnUpdate is a validation group used to specify that certain validation rules should only be
 * applied when updating an existing entity. This allows for different validation logic to be
 * executed during creation and update operations, ensuring that the appropriate constraints are
 * enforced based on the context of the operation.
 *
 * @author siujamo
 */
public interface OnUpdate {
}
