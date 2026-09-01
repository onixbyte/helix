package com.onixbyte.helix.service;

import com.onixbyte.helix.domain.database.query.wrapper.QueryRoleWrapper;
import com.onixbyte.helix.domain.entity.Role;
import com.onixbyte.helix.domain.web.request.QueryRoleRequest;
import com.onixbyte.helix.domain.web.request.RoleRequest;
import com.onixbyte.helix.enumeration.Status;
import com.onixbyte.helix.exception.BizException;
import com.onixbyte.helix.manager.RoleAuthorityManager;
import com.onixbyte.helix.manager.RoleManager;
import com.onixbyte.helix.manager.UserRoleManager;
import com.onixbyte.helix.shared.MessageName;
import com.onixbyte.helix.utils.MessageUtil;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    private final RoleManager roleManager;
    private final RoleAuthorityManager roleAuthorityManager;
    private final UserRoleManager userRoleManager;
    private final MessageUtil messageUtil;

    @Autowired
    public RoleService(
            RoleManager roleManager,
            RoleAuthorityManager roleAuthorityManager,
            UserRoleManager userRoleManager,
            MessageUtil messageUtil
    ) {
        this.roleManager = roleManager;
        this.roleAuthorityManager = roleAuthorityManager;
        this.userRoleManager = userRoleManager;
        this.messageUtil = messageUtil;
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

    public Role addRole(RoleRequest request) {
        var isDefaultRole = Optional.of(request)
                .map(RoleRequest::defaultValue)
                .orElse(false);
        var status = Optional.of(request)
                .map(RoleRequest::status)
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
    public Role editRole(Long id, RoleRequest request) {
        return roleManager.fullUpdateById(id, Role.builder()
                .name(request.name())
                .code(request.code())
                .sort(request.sort())
                .defaultValue(request.defaultValue())
                .description(request.description())
                .status(request.status())
                .build());
    }

    @Transactional
    public String deleteRole(Long id) {
        var role = roleManager.getRoleById(id)
                .orElseThrow(() -> new BizException(
                        HttpStatus.NOT_FOUND,
                        messageUtil.getMessage(MessageName.ROLE_NOT_FOUND, id))
                );

        roleAuthorityManager.deleteByRoleId(id);
        userRoleManager.deleteByRoleId(id);
        roleManager.deleteRole(id);

        return role.getName();
    }
}
