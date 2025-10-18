package com.onixbyte.helix.repository;

import com.onixbyte.helix.domain.common.PageRequest;
import com.onixbyte.helix.domain.entity.Department;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository {

    /**
     * Select all departments.
     *
     * @return all departments
     */
    List<Department> selectAll(@Param("pageRequest") PageRequest pageRequest);
}
