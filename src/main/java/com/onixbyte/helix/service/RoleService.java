package com.onixbyte.helix.service;

import com.onixbyte.helix.constant.Status;
import com.onixbyte.helix.domain.database.query.wrapper.QueryRoleWrapper;
import com.onixbyte.helix.domain.entity.Role;
import com.onixbyte.helix.domain.web.request.QueryRoleRequest;
import com.onixbyte.helix.manager.RoleManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    private final RoleManager roleManager;

    public RoleService(RoleManager roleManager) {
        this.roleManager = roleManager;
    }

    public Page<Role> getRoles(Pageable pageable, QueryRoleRequest request) {
        QueryRoleWrapper wrapper = new QueryRoleWrapper();

        Optional.ofNullable(request.name())
                .filter(StringUtils::isNotBlank)
                .ifPresent(wrapper::setName);

        Optional.ofNullable(request.code())
                .filter(StringUtils::isNotBlank)
                .ifPresent(wrapper::setCode);

        Optional.ofNullable(request.status())
                .filter(StringUtils::isNotBlank)
                .map(Status::valueOf)
                .ifPresent(wrapper::setStatus);

        return roleManager.selectAll(pageable, wrapper);
    }
}
