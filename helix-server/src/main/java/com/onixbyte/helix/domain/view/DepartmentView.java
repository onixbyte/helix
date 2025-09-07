package com.onixbyte.helix.domain.view;

import com.onixbyte.helix.constant.NormalStatus;

import java.io.Serializable;

/**
 * A view object for Department.
 *
 * @author zihluwang
 */
public record DepartmentView(
        String id,
        String name,
        String parentId,
        String treePath,
        Integer sortOrder,
        NormalStatus status
) implements Serializable {
}