package com.onixbyte.helix.service;

import com.onixbyte.helix.domain.entity.Department;
import com.onixbyte.helix.domain.model.TreeNode;
import com.onixbyte.helix.manager.DepartmentManager;
import com.onixbyte.helix.utils.TreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentManager departmentManager;

    @Autowired
    public DepartmentService(DepartmentManager departmentManager) {
        this.departmentManager = departmentManager;
    }

    public TreeNode<Department> getDepartmentTree() {
        var departments = departmentManager.selectAll(Pageable.unpaged());
        return TreeUtil.buildTree(departments.getContent());
    }

    public List<Department> getDepartments() {
        return departmentManager.selectAll(Pageable.unpaged()).getContent();
    }
}
