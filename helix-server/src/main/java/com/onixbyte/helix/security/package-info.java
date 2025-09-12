/**
 * Security components and authentication/authorisation infrastructure for the Helix application.
 * <p>
 * This package contains security-related components that handle authentication, authorisation, and
 * security policies. The security architecture is built on Spring Security framework and integrates
 * with Microsoft Authentication Library (MSAL) for enterprise authentication scenarios.
 * <p>
 * <strong>Security Architecture:</strong> The Helix application implements a comprehensive security
 * model that supports multiple authentication mechanisms, role-based access control (RBAC), and
 * fine-grained authorisation policies. The security layer is designed to be extensible and
 * configurable for different deployment environments.
 * <p>
 * <strong>Intended Contents:</strong>
 * <ul>
 * <li>
 *   <strong>Authentication Providers:</strong> Custom authentication mechanisms including MSAL
 *   integration and JWT token validation
 * </li>
 * <li>
 *   <strong>Authorisation Components:</strong> Role-based and permission-based access
 *   control implementations
 * </li>
 * <li>
 *   <strong>Security Filters:</strong> Custom security filters for request processing and security
 *   context management
 * </li>
 * <li>
 *   <strong>Token Handlers:</strong> JWT token generation, validation, and refresh mechanisms
 * </li>
 * <li>
 *   <strong>Security Utilities:</strong> Cryptographic utilities, password encoders, and security
 *   helper classes
 * </li>
 * <li>
 *   <strong>Security Configurations:</strong> Security policy definitions and configuration classes
 * </li>
 * </ul>
 * <p>
 * <strong>Authentication Mechanisms:</strong>
 * <ul>
 * <li>
 *   <strong>MSAL Integration:</strong> Microsoft Authentication Library integration for Azure
 *   AD authentication
 * </li>
 * <li>
 *   <strong>JWT Tokens:</strong> JSON Web Token-based authentication for stateless security
 * </li>
 * <li>
 *   <strong>Session Management:</strong> Traditional session-based authentication where required
 * </li>
 * <li>
 *   <strong>API Key Authentication:</strong> Service-to-service authentication mechanisms
 * </li>
 * </ul>
 * <p>
 * <strong>Security Best Practices:</strong>
 * <ul>
 * <li>
 *   <strong>Principle of Least Privilege:</strong> Grant minimum necessary permissions for
 *   each role
 * </li>
 * <li>
 *   <strong>Defence in Depth:</strong> Implement multiple layers of security controls
 * </li>
 * <li>
 *   <strong>Secure by Default:</strong> Default configurations should be secure and require
 *   explicit relaxation
 * </li>
 * <li>
 *   <strong>Audit Logging:</strong> Log all security-relevant events for monitoring and compliance
 * </li>
 * <li>
 *   <strong>Input Validation:</strong> Validate and sanitise all inputs at security boundaries
 * </li>
 * </ul>
 * <p>
 * All security components in this package should follow OWASP security guidelines and be regularly
 * updated to address emerging security threats.
 *
 * @author zihluwang
 * @since 1.0.0
 * @see com.onixbyte.helix.config
 * @see com.onixbyte.helix.properties
 * @see org.springframework.security.core
 */
package com.onixbyte.helix.security;