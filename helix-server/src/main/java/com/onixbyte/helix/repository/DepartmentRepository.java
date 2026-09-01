package com.onixbyte.helix.repository;

import com.onixbyte.helix.domain.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query("""
            select max(d.sort)
            from Department d
            where (:parentId is null and d.parentId is null)
               or d.parentId = :parentId
            """)
    Integer findMaxSort(Long parentId);

    boolean existsByName(String name);
}
