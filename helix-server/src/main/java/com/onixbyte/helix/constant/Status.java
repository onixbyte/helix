package com.onixbyte.helix.constant;

/**
 * Enumeration representing general status states for system entities.
 * <p>
 * This enumeration provides a standardised way to represent the operational status of various
 * system entities, resources, or components within the Helix application. It offers a binary state
 * model that can be applied across different domain objects to indicate their current
 * operational condition.
 * <p>
 * The status values are designed to be generic and reusable across multiple contexts, such as
 * system configurations, feature toggles, service states, or any other binary operational
 * indicators within the application.
 *
 * @author zihluwang
 * @since 1.0.0
 */
public enum Status {

    /**
     * Indicates that the entity is currently active and operational.
     * <p>
     * When an entity has an ACTIVE status, it means the entity is:
     * <ul>
     *   <li>Currently enabled and functioning</li>
     *   <li>Available for use by the system or users</li>
     *   <li>Participating in normal application operations</li>
     * </ul>
     */
    ACTIVE,

    /**
     * Indicates that the entity is currently inactive or disabled.
     * <p>
     * When an entity has an INACTIVE status, it means the entity is:
     * <ul>
     *   <li>Currently disabled or not functioning</li>
     *   <li>Temporarily or permanently unavailable</li>
     *   <li>Excluded from normal application operations</li>
     * </ul>
     */
    INACTIVE
}
