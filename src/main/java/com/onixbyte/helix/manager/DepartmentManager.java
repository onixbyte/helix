package com.onixbyte.helix.manager;

import com.onixbyte.helix.domain.entity.Department;
import com.onixbyte.helix.exception.BizException;
import com.onixbyte.helix.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class DepartmentManager {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentManager(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Page<Department> selectAll(Pageable pageable) {
        return departmentRepository.findAll(pageable);
    }

    public Department selectById(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }

    public Integer getNextSort(Long parentId) {
        return Optional.ofNullable(departmentRepository.findMaxSort(parentId)).orElse(0) + 1;
    }

    public Department save(Department department) {
        return departmentRepository.save(department);
    }

    /**
     * Fully updates an existing department by ID.
     * <p>
     * The method loads the target department, replaces mutable fields ({@code name},
     * {@code parentId}, {@code sort}, {@code status}), and refreshes {@code updatedAt} to the
     * current time.
     *
     * @param id         the ID of the department to update
     * @param department the source data carrying new field values
     * @return the managed and updated {@link Department} entity
     * @throws BizException if the target department does not exist
     */
    @Transactional
    public Department fullUpdateById(Long id, Department department) {
        var updatedAt = LocalDateTime.now();

        var departmentToEdit = departmentRepository.findById(id)
                .orElseThrow(() -> new BizException(
                        HttpStatus.NOT_FOUND,
                        "Department (ID: %d) to be edited not found.".formatted(department.getId()))
                );

        departmentToEdit.setName(department.getName());
        departmentToEdit.setParentId(department.getParentId());
        departmentToEdit.setSort(department.getSort());
        departmentToEdit.setStatus(department.getStatus());
        departmentToEdit.setUpdatedAt(updatedAt);
        return departmentToEdit;
    }
}
