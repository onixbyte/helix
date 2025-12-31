package com.onixbyte.helix.controller;

import com.onixbyte.helix.domain.entity.Department;
import com.onixbyte.helix.domain.common.TreeNode;
import com.onixbyte.helix.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/tree")
    public TreeNode<Department> getDepartmentTree() {
        return departmentService.getDepartmentTree();
    }

    @GetMapping
    public List<Department> getDepartments() {
        return departmentService.getDepartments();
    }
}
