package com.onixbyte.helix.service;

import com.onixbyte.helix.domain.common.PageRequest;
import com.onixbyte.helix.domain.entity.Department;
import com.onixbyte.helix.domain.model.TreeNode;
import com.onixbyte.helix.manager.DepartmentManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentManager departmentManager;

    public DepartmentService(DepartmentManager departmentManager) {
        this.departmentManager = departmentManager;
    }

    public List<TreeNode<Department>> getDepartmentTree() {
        var departments = departmentManager.selectAll(PageRequest.unpaged());
        return departmentManager.buildTree(departments);
    }
}
