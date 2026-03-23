package com.onixbyte.helix.service;

import com.onixbyte.helix.domain.common.TreeNode;
import com.onixbyte.helix.domain.entity.Department;
import com.onixbyte.helix.domain.web.request.AddDepartmentRequest;
import com.onixbyte.helix.enumeration.Status;
import com.onixbyte.helix.manager.DepartmentManager;
import com.onixbyte.helix.utils.TreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    @Transactional(rollbackFor = Throwable.class)
    public Department addDepartment(AddDepartmentRequest request) {
        var createdAt = LocalDateTime.now();

        var parentId = request.parentId();
        var sort = Optional.ofNullable(request.sort())
                .orElseGet(() -> departmentManager.getNextSort(parentId));

        return departmentManager.save(Department.builder()
                .name(request.name())
                .parentId(parentId)
                .sort(sort)
                .status(Optional.ofNullable(request.status()).orElse(Status.ACTIVE))
                .createdAt(createdAt)
                .updatedAt(createdAt)
                .build());
    }
}
