/**
 * Extension and plugin support package for the Helix application.
 * <p>
 * This package is designed to contain extension points, plugin interfaces,
 * and extensibility mechanisms that allow the Helix application to be
 * extended with additional functionality without modifying core components.
 * <p>
 * <strong>Intended Contents:</strong>
 * <ul>
 * <li><strong>Extension Interfaces:</strong> Define contracts for pluggable components</li>
 * <li><strong>Plugin Loaders:</strong> Mechanisms for discovering and loading extensions</li>
 * <li>
 *   <strong>Extension Points:</strong> Well-defined points where custom functionality can
 *   be injected
 * </li>
 * <li><strong>Custom Annotations:</strong> Annotations for marking and configuring extensions</li>
 * </ul>
 * <p>
 * <strong>Design Principles:</strong>
 * <ul>
 * <li>
 *   <strong>Loose Coupling:</strong> Extensions should be loosely coupled to core functionality
 * </li>
 * <li>
 *   <strong>Service Provider Interface (SPI):</strong> Use SPI patterns for plugin discovery
 * </li>
 * <li>
 *   <strong>Configuration-Driven:</strong> Allow extensions to be configured through
 *   application properties
 * </li>
 * <li>
 *   <strong>Lifecycle Management:</strong> Provide proper initialisation and cleanup for extensions
 * </li>
 * </ul>
 * <p>
 * This package follows the extensibility patterns commonly used in enterprise applications to
 * support customisation and third-party integrations whilst maintaining system stability
 * and performance.
 *
 * @author zihluwang
 * @since 1.0.0
 */
package com.onixbyte.helix.extension;