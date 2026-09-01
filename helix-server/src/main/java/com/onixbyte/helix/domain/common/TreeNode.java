package com.onixbyte.helix.domain.common;

import java.util.ArrayList;
import java.util.List;

public record TreeNode<T>(
        T item,
        List<TreeNode<T>> children
) {

    /**
     * Helper constructor for building.
     * @param item the item
     */
    public TreeNode(T item) {
        this(item, new ArrayList<>());
    }
}
