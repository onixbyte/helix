/**
 * MapStruct mapper interfaces for object mapping and transformation in the Helix application.
 * <p>
 * This package contains MapStruct mapper interfaces that define the contract for converting between
 * different object types, particularly between domain entities, DTOs (Data Transfer Objects), and
 * view models. These interfaces are automatically implemented by the MapStruct annotation processor
 * at compile time, providing type-safe and performant object mapping.
 * <p>
 * <strong>MapStruct Integration:</strong> The Helix application uses MapStruct as its object
 * mapping framework to eliminate boilerplate code for converting between different
 * object representations. Mapper interfaces are processed at compile time to generate efficient
 * mapping implementations.
 * <p>
 * <strong>Intended Contents:</strong>
 * <ul>
 * <li><strong>Entity to DTO Mappers:</strong> Convert domain entities to data transfer objects</li>
 * <li><strong>DTO to Entity Mappers:</strong> Convert DTOs back to domain entities</li>
 * <li><strong>View Model Mappers:</strong> Transform entities to presentation layer models</li>
 * <li><strong>Request/Response Mappers:</strong> Handle API request and response transformations</li>
 * <li><strong>Aggregate Mappers:</strong> Complex mappings involving multiple related objects</li>
 * </ul>
 * <p>
 * <strong>Naming Conventions:</strong>
 * <ul>
 * <li><strong>Interface Names:</strong> Follow the pattern {@code EntityMapper}
 *     (e.g., {@code UserMapper}, {@code RoleMapper})</li>
 * <li><strong>Method Names:</strong> Use descriptive names that clearly indicate
 *     the mapping direction (e.g., {@code toDto}, {@code toEntity}, {@code toViewModel})</li>
 * <li><strong>Mapping Methods:</strong> Define clear input and output types for
 *     each transformation method</li>
 * </ul>
 * <p>
 * <strong>Best Practices:</strong>
 * <ul>
 * <li><strong>Mapping Configuration:</strong> Use {@code @Mapping} annotations
 *     to handle field name differences and custom transformations</li>
 * <li><strong>Null Handling:</strong> Configure appropriate null value strategies
 *     using {@code @Mapper} annotation parameters</li>
 * <li><strong>Custom Mappings:</strong> Implement {@code @Named} methods for
 *     complex field transformations</li>
 * <li><strong>Performance:</strong> Leverage MapStruct's compile-time generation
 *     for optimal runtime performance</li>
 * <li><strong>Testing:</strong> Write unit tests for complex mapping scenarios
 *     to ensure correctness</li>
 * </ul>
 * <p>
 * All mapper interfaces in this package should be annotated with {@code @Mapper} and configured
 * with appropriate component model settings for Spring integration.
 *
 * @author zihluwang
 * @since 1.0.0
 * @see org.mapstruct.Mapper
 * @see org.mapstruct.Mapping
 * @see com.onixbyte.helix.domain.entity
 * @see com.onixbyte.helix.controller.dto
 */
package com.onixbyte.helix.mapper;