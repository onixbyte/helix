package com.onixbyte.helix.repository;

import com.onixbyte.helix.domain.entity.Department;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository {

    List<Department> selectAll();
}
