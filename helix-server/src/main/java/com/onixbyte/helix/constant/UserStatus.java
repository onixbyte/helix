package com.onixbyte.helix.constant;

/**
 * Enumeration representing the various states of user accounts within the system.
 * <p>
 * This enumeration defines the possible status values for user accounts in the Helix application,
 * providing a standardised way to manage user account lifecycle and access control. Each status
 * represents a different level of account accessibility and operational capability within
 * the system.
 * <p>
 * User status directly affects authentication, authorisation, and system access permissions.
 * The status is typically managed through administrative functions and security policies to ensure
 * proper access control and account management.
 *
 * @author zihluwang
 * @since 1.0.0
 * @see com.onixbyte.helix.config.SecurityConfiguration
 */
public enum UserStatus {

    /**
     * Indicates that the user account is active and fully operational.
     * <p>
     * An ACTIVE user account has:
     * <ul>
     *   <li>Full access to system features and resources</li>
     *   <li>Ability to authenticate and establish sessions</li>
     *   <li>Normal operational privileges as per assigned roles</li>
     *   <li>No restrictions on account usage</li>
     * </ul>
     */
    ACTIVE,

    /**
     * Indicates that the user account is inactive or disabled.
     * <p>
     * An INACTIVE user account:
     * <ul>
     *   <li>Cannot authenticate or access the system</li>
     *   <li>Is temporarily or permanently disabled</li>
     *   <li>Retains user data but blocks all system access</li>
     *   <li>May be reactivated by administrators if appropriate</li>
     * </ul>
     */
    INACTIVE,

    /**
     * Indicates that the user account is locked due to security concerns.
     * <p>
     * A LOCKED user account:
     * <ul>
     *   <li>Is temporarily blocked from system access</li>
     *   <li>May result from failed authentication attempts</li>
     *   <li>Could be locked due to security policy violations</li>
     *   <li>Requires administrative intervention to unlock</li>
     *   <li>Maintains user data whilst preventing access</li>
     * </ul>
     */
    LOCKED
}
