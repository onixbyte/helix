package com.onixbyte.helix.manager;

import com.onixbyte.helix.domain.entity.Department;
import com.onixbyte.helix.mapper.DepartmentMapper;
import com.onixbyte.helix.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DepartmentManager {

    private final DepartmentMapper departmentMapper;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentManager(DepartmentMapper departmentMapper, DepartmentRepository departmentRepository) {
        this.departmentMapper = departmentMapper;
        this.departmentRepository = departmentRepository;
    }

    public Page<Department> selectAll(Pageable pageable) {
        return departmentRepository.findAll(pageable);
    }
}
