package com.onixbyte.helix.controller;

import com.onixbyte.helix.domain.entity.Role;
import com.onixbyte.helix.domain.web.request.QueryRoleRequest;
import com.onixbyte.helix.domain.web.request.RoleRequest;
import com.onixbyte.helix.domain.web.response.ActionResponse;
import com.onixbyte.helix.service.RoleService;
import com.onixbyte.helix.shared.MessageName;
import com.onixbyte.helix.utils.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * This controller provides entry points to manipulate roles.
 *
 * @author zihluwang
 * @author siujamo
 */
@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;
    private final MessageUtil messageUtil;

    @Autowired
    public RoleController(RoleService roleService, MessageUtil messageUtil) {
        this.roleService = roleService;
        this.messageUtil = messageUtil;
    }

    @GetMapping
    public Page<Role> getRoles(
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @Validated @ModelAttribute QueryRoleRequest request
    ) {
        var pageRequest = PageRequest.of(pageNum - 1, pageSize, Sort.by(Sort.Order.asc("id")));
        return roleService.getRoles(pageRequest, request);
    }

    @PostMapping
    public Role addRole(@Validated @RequestBody RoleRequest request) {
        return roleService.addRole(request);
    }

    @PutMapping("/{id:\\d+}")
    public Role editRole(
            @PathVariable Long id,
            @Validated @RequestBody RoleRequest request
    ) {
        return roleService.editRole(id, request);
    }

    @DeleteMapping("/{id:\\d+}")
    public ActionResponse deleteRole(@PathVariable Long id) {
        var name = roleService.deleteRole(id);
        return ActionResponse.success(messageUtil.getMessage(MessageName.ROLE_DELETED, name));
    }
}
