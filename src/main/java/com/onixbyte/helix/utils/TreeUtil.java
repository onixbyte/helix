package com.onixbyte.helix.utils;

import com.onixbyte.helix.domain.common.Treeable;
import com.onixbyte.helix.domain.model.TreeNode;
import com.onixbyte.helix.exception.BizException;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.HttpStatus;

import java.util.*;
import java.util.stream.Collectors;

public class TreeUtil {

    /**
     * Builds a tree from a flat list of items that implement Treeable.
     *
     * @param items the flat list of treeable items
     * @param <T>   the type extending Treeable
     * @return a single root TreeNode
     */
    public static <K, T extends Treeable<K>> TreeNode<T> buildTree(List<T> items) {
        if (CollectionUtils.isEmpty(items)) {
            return null;
        }

        // A map to quickly access the TreeNode corresponding to a node ID.
        // This is crucial for building the hierarchical links between parent and child nodes.
        var idToNodeMap = items.stream()
                .collect(Collectors.toMap(Treeable::getId, (item) -> new TreeNode<>(item, new ArrayList<>())));

        // Get root node
        var rootItems = items.stream()
                .filter((item) -> item.getParentId() == null)
                .toList();

        // Ensure only 1 root node is included
        if (rootItems.size() > 1) {
            throw new BizException(HttpStatus.INTERNAL_SERVER_ERROR, "Multiple root items found in given values.");
        }

        // Get root item and build root node
        var rootItem = rootItems.get(0);
        var rootNode = new TreeNode<T>(rootItem);
        idToNodeMap.remove(rootItem.getId());

        // Iterate through all departments to build the hierarchy.
        for (var item : items) {
            var currentNode = idToNodeMap.get(item.getId());
            // If it has a parentId, find its parent TreeNode and add the current node to its children.
            var parentNode = Objects.equals(item.getParentId(), rootItem.getId()) ? rootNode : idToNodeMap.get(item.getParentId());
            if (parentNode != null) { // Ensure the parent actually exists in the provided list
                parentNode.children().add(currentNode);
            }
        }

        sortChildrenRecursively(rootNode.children());

        return rootNode;
    }

    /**
     * Builds a forest from a flat list of items that implement Treeable.
     *
     * @param items the flat list of treeable items
     * @param <T>   the type extending Treeable
     * @return a list of root TreeNodes
     */
    public static <K, T extends Treeable<K>> List<TreeNode<T>> buildForest(List<T> items) {
        if (CollectionUtils.isEmpty(items)) {
            return Collections.emptyList();
        }

        // A map to quickly access the TreeNode corresponding to a node ID.
        // This is crucial for building the hierarchical links between parent and child nodes.
        var idToNodeMap = items.stream()
                .collect(Collectors.toMap(Treeable::getId, (item) -> new TreeNode<>(item, new ArrayList<>())));

        // A list to store the top-level (root) departments.
        var rootNodes = new ArrayList<TreeNode<T>>();

        // Iterate through all departments to build the hierarchy.
        for (var item : items) {
            var currentNode = idToNodeMap.get(item.getId());

            if (Objects.isNull(item.getParentId())) {
                // If a department has no parentId, it's a root department.
                rootNodes.add(currentNode);
            } else {
                // If it has a parentId, find its parent TreeNode and add the current node to its children.
                var parentNode = idToNodeMap.get(item.getParentId());
                if (parentNode != null) { // Ensure the parent actually exists in the provided list
                    parentNode.children().add(currentNode);
                }
            }
        }

        // 4. Sort the children lists for all nodes, starting from the root nodes, to ensure consistent display order.
        sortChildrenRecursively(rootNodes);

        return rootNodes;
    }

    /**
     * Recursively sorts the children list of each TreeNode based on the 'sort' field of its Department item.
     * This method traverses the tree structure to apply sorting at every level.
     *
     * @param nodes The list of TreeNodes to sort (e.g., the initial list of root nodes, or a sub-list of children).
     */
    private static <K, T extends Treeable<K>> void sortChildrenRecursively(List<TreeNode<T>> nodes) {
        if (nodes == null || nodes.isEmpty()) {
            return;
        }

        // Define the comparator for sorting children based on the 'sort' field of the menu item.
        var comparator = Comparator.<TreeNode<T>, Integer>comparing(
                (node) -> node.item().getSort(),
                Comparator.nullsLast(Comparator.naturalOrder()) // Handle null 'sort' values by placing them last.
        );

        // Sort the current list of nodes (e.g., root nodes, or a parent's immediate children).
        nodes.sort(comparator);

        // Recursively apply sorting to the children of each node in the current list.
        for (var node : nodes) {
            if (node.children() != null && !node.children().isEmpty()) {
                sortChildrenRecursively(node.children());
            }
        }
    }
}
