package com.onixbyte.helix.manager;

import com.onixbyte.helix.domain.common.PageRequest;
import com.onixbyte.helix.domain.entity.Department;
import com.onixbyte.helix.domain.model.TreeNode;
import com.onixbyte.helix.repository.DepartmentRepository;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DepartmentManager {

    private final DepartmentRepository departmentRepository;

    public DepartmentManager(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> selectAll(PageRequest pageRequest) {
        return departmentRepository.selectAll(pageRequest);
    }

    /**
     * Organises a flat list of Department objects into a list of
     * {@code TreeNode<Department>} objects. Each TreeNode's item will be a Department, and its
     * children list will contain that {@code Department}'s direct sub-departments
     * (also of type Department).
     *
     * @param departments the original flat list of {@code Department} objects
     * @return the organised list of {@code TreeNode<Department>} objects.
     */
    public List<TreeNode<Department>> buildTree(List<Department> departments) {
        if (departments == null || departments.isEmpty()) {
            return List.of();
        }

        // A map to quickly access the TreeNode corresponding to a Department ID.
        // This is crucial for building the hierarchical links between parent and child nodes.
        var idToNodeMap = new HashMap<Long, TreeNode<Department>>();

        // 1. Create an initial TreeNode for each Department and populate the map.
        // Each TreeNode starts with an empty list of children.
        for (var dept : departments) {
            idToNodeMap.put(dept.getId(), new TreeNode<>(dept, new ArrayList<>()));
        }

        // 2. A list to store the top-level (root) departments.
        List<TreeNode<Department>> rootNodes = new ArrayList<>();

        // 3. Iterate through all departments to build the hierarchy.
        for (Department dept : departments) {
            TreeNode<Department> currentNode = idToNodeMap.get(dept.getId());

            if (dept.getParentId() == null) {
                // If a department has no parentId, it's a root department.
                rootNodes.add(currentNode);
            } else {
                // If it has a parentId, find its parent TreeNode and add the current node to its children.
                TreeNode<Department> parentNode = idToNodeMap.get(dept.getParentId());
                if (parentNode != null) { // Ensure the parent actually exists in the provided list
                    parentNode.children().add(currentNode);
                }
                // Note: If a parentId is specified but the parent department is not found in the list,
                // this child department will effectively be orphaned and not appear in the resulting tree.
                // In a real application, you might want to log this or handle it as an error.
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
    private void sortChildrenRecursively(List<TreeNode<Department>> nodes) {
        if (nodes == null || nodes.isEmpty()) {
            return;
        }

        // Define the comparator for sorting children based on the 'sort' field of the Department item.
        var comparator = Comparator.<TreeNode<Department>, Integer>comparing(
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
