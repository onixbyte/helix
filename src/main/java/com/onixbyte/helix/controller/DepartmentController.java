package com.onixbyte.helix.controller;

import com.onixbyte.helix.domain.entity.Department;
import com.onixbyte.helix.domain.common.TreeNode;
import com.onixbyte.helix.domain.web.request.AddDepartmentRequest;
import com.onixbyte.helix.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This controller provides entry points that manipulates departments.
 *
 * @author zihluwang
 * @author siujamo
 */
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

    @PostMapping
    public Department addDepartment(@Validated @RequestBody AddDepartmentRequest request) {
        return departmentService.addDepartment(request);
    }
}
