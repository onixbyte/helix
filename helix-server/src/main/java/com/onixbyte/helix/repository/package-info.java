/**
 * Repository layer interfaces and implementations for the Helix application.
 * <p>
 * This package contains repository interfaces that define the contract for data access operations.
 * Following the Repository pattern, these interfaces provide an abstraction layer between the
 * domain model and data mapping layers, enabling clean separation of concerns and testability.
 * <p>
 * <strong>Repository Pattern:</strong> The Helix application implements the Repository pattern to
 * encapsulate the logic needed to access data sources. This centralises common data access
 * functionality, providing better maintainability and decoupling the infrastructure or technology
 * used to access databases from the domain model layer.
 * <p>
 * <strong>Intended Contents:</strong>
 * <ul>
 * <li><strong>Domain Repositories:</strong> Interfaces for core domain entities</li>
 * <li><strong>Query Repositories:</strong> Specialised repositories for complex queries</li>
 * <li><strong>Aggregate Repositories:</strong> Repositories for domain aggregates</li>
 * <li><strong>Custom Repositories:</strong> Domain-specific data access patterns</li>
 * <li>
 *   <strong>Repository Implementations:</strong> Concrete implementations using MyBatis mappers or
 *   other data access technologies
 * </li>
 * </ul>
 * <p>
 * <strong>Design Guidelines:</strong>
 * <ul>
 * <li>
 *   <strong>Domain-Centric:</strong> Repository methods should reflect domain concepts rather than
 *   database operations
 * </li>
 * <li>
 *   <strong>Interface Segregation:</strong> Create focused interfaces for specific use cases rather
 *   than large, monolithic repositories
 * </li>
 * <li>
 *   <strong>Aggregate Boundaries:</strong> Repositories should typically work with aggregate roots,
 *   not individual entities
 * </li>
 * <li>
 *   <strong>Query Methods:</strong> Use expressive method names that clearly indicate the query
 *   intent (e.g., {@code findActiveUsersByRole})
 * </li>
 * </ul>
 * <p>
 * <strong>Implementation Strategy:</strong>
 * <ul>
 * <li>
 *   <strong>MyBatis Integration:</strong> Repository implementations delegate to MyBatis mappers
 *   for actual database operations
 * </li>
 * <li>
 *   <strong>Transaction Management:</strong> Repositories should not manage transactions directly;
 *   delegate to service layer
 * </li>
 * <li>
 *   <strong>Exception Translation:</strong> Convert data access exceptions to
 *   domain-specific exceptions
 * </li>
 * <li>
 *   <strong>Caching Strategy:</strong> Implement appropriate caching at the repository level for
 *   performance optimisation
 * </li>
 * </ul>
 * <p>
 * Repository interfaces in this package are typically implemented by classes that use
 * {@link com.onixbyte.helix.mapper} components for actual data persistence operations.
 *
 * @author zihluwang
 * @since 1.0.0
 * @see com.onixbyte.helix.mapper
 * @see com.onixbyte.helix.domain.entity
 * @see com.onixbyte.helix.service
 */
package com.onixbyte.helix.repository;