package com.onixbyte.helix.domain.model;

import java.util.List;

public record TreeNode<T>(
        T item,
        List<TreeNode<T>> children
) {
}
