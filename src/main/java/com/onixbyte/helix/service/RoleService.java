package com.onixbyte.helix.service;

import com.onixbyte.helix.constant.Status;
import com.onixbyte.helix.domain.database.query.wrapper.QueryRoleWrapper;
import com.onixbyte.helix.domain.entity.Role;
import com.onixbyte.helix.domain.web.request.AddRoleRequest;
import com.onixbyte.helix.domain.web.request.EditRoleRequest;
import com.onixbyte.helix.domain.web.request.QueryRoleRequest;
import com.onixbyte.helix.manager.RoleAuthorityManager;
import com.onixbyte.helix.manager.RoleManager;
import com.onixbyte.helix.manager.UserRoleManager;
import com.onixbyte.helix.repository.UserRoleRepository;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    private final RoleManager roleManager;
    private final RoleAuthorityManager roleAuthorityManager;
    private final UserRoleManager userRoleManager;

    @Autowired
    public RoleService(
            RoleManager roleManager,
            RoleAuthorityManager roleAuthorityManager,
            UserRoleManager userRoleManager
    ) {
        this.roleManager = roleManager;
        this.roleAuthorityManager = roleAuthorityManager;
        this.userRoleManager = userRoleManager;
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

    public Role addRole(AddRoleRequest request) {
        var isDefaultRole = Optional.ofNullable(request.defaultValue())
                .orElse(false);
        var status = Optional.ofNullable(request.status())
                .map(Status::valueOf)
                .orElse(Status.ACTIVE);

        var role = Role.builder()
                .name(request.name())
                .code(request.code())
                .sort(request.sort())
                .defaultValue(isDefaultRole)
                .description(request.description())
                .status(status)
                .build();

        return roleManager.save(role);
    }

    @Transactional
    public void editRole(EditRoleRequest request) {
        roleManager.updateRole(Role.builder()
                .id(request.id())
                .name(request.name())
                .code(request.code())
                .sort(request.sort())
                .defaultValue(request.defaultValue())
                .description(request.description())
                .status(Optional.ofNullable(request.status())
                        .map(Status::valueOf)
                        .orElse(null))
                .build());
    }

    @Transactional
    public void deleteRole(Long id) {
        roleAuthorityManager.deleteByRoleId(id);
        userRoleManager.deleteByRoleId(id);
        roleManager.deleteRole(id);
    }
}
