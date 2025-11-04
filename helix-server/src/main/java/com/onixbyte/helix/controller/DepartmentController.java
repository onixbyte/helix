package com.onixbyte.helix.controller;

import com.onixbyte.helix.domain.entity.Department;
import com.onixbyte.helix.domain.model.TreeNode;
import com.onixbyte.helix.domain.web.request.AddDepartmentRequest;
import com.onixbyte.helix.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/tree")
    public List<TreeNode<Department>> getDepartmentTree() {
        return departmentService.getDepartmentTree();
    }

    @PostMapping
    public Department addDepartment(@RequestBody AddDepartmentRequest request) {
        return departmentService.addDepartment(request);
    }
}
