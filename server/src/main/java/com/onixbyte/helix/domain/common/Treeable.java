package com.onixbyte.helix.domain.common;

/**
 * Represents an element that can be part of a tree structure.
 * <p>
 * This interface provides the basic methods necessary for an object to be identified,
 * linked to a parent, and sorted within its siblings in a tree-like hierarchy.
 *
 * @param <K> the type of the key used for identification
 */
public interface Treeable<K> {
    /**
     * Retrieves the sort order value for this element amongst its siblings.
     *
     * @return the integer sort value
     */
    Integer getSort();

    /**
     * Retrieves the unique identifier for this element.
     *
     * @return the element's unique key
     */
    K getId();

    /**
     * Retrieves the identifier of this element's parent.
     * <p>
     * If the element is a root element, this method should return {@code null}.
     *
     * @return the key of the parent element, or {@code null} if it is a root
     */
    K getParentId();
}
