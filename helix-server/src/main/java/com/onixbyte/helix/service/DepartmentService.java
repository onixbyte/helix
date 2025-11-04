package com.onixbyte.helix.service;

import com.onixbyte.helix.constant.Status;
import com.onixbyte.helix.domain.common.PageRequest;
import com.onixbyte.helix.domain.entity.Department;
import com.onixbyte.helix.domain.model.TreeNode;
import com.onixbyte.helix.domain.web.request.AddDepartmentRequest;
import com.onixbyte.helix.exception.BizException;
import com.onixbyte.helix.manager.DepartmentManager;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    public Department addDepartment(AddDepartmentRequest request) {
        var createTime = LocalDateTime.now();
        try {
            var status = Status.valueOf(request.status());
            return departmentManager.save(Department.builder()
                    .name(request.name())
                    .parentId(request.parentId())
                    .sort(request.sort())
                    .status(status)
                    .createdAt(createTime)
                    .updatedAt(createTime)
                    .build());
        } catch (IllegalArgumentException e) {
            throw new BizException(HttpStatus.BAD_REQUEST, "错误的部门状态");
        }
    }
}
