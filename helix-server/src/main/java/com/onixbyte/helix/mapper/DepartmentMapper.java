package com.onixbyte.helix.mapper;

import com.onixbyte.helix.domain.biz.BizDepartment;
import com.onixbyte.helix.domain.entity.Department;
import com.onixbyte.helix.domain.view.DepartmentView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * A mapper for Department.
 *
 * @author zihluwang
 */
@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    /**
     * Convert Department entity to BizDepartment.
     *
     * @param department the Department entity
     * @return the BizDepartment object
     */
    @Mappings({
            // Ignore createdAt and updatedAt fields when converting to business object
    })
    BizDepartment asBusiness(Department department);

    /**
     * Convert BizDepartment to DepartmentView.
     *
     * @param bizDepartment the BizDepartment object
     * @return the DepartmentView object
     */
    @Mappings({
            @Mapping(target = "id", expression = "java(bizDepartment.getId() != null ? bizDepartment.getId().toString() : null)"),
            @Mapping(target = "parentId", expression = "java(bizDepartment.getParentId() != null ? bizDepartment.getParentId().toString() : null)")
    })
    DepartmentView asView(BizDepartment bizDepartment);
}