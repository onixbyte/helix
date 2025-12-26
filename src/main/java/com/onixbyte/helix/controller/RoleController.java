package com.onixbyte.helix.controller;

import com.onixbyte.helix.domain.entity.Role;
import com.onixbyte.helix.domain.web.request.AddRoleRequest;
import com.onixbyte.helix.domain.web.request.EditRoleRequest;
import com.onixbyte.helix.domain.web.request.QueryRoleRequest;
import com.onixbyte.helix.service.RoleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
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
    public ResponseEntity<Void> addRole(@Validated @RequestBody AddRoleRequest request) {
        roleService.addRole(request);
        return ResponseEntity.ok(null);
    }

    @PutMapping
    public ResponseEntity<Void> editRole(@Validated @RequestBody EditRoleRequest request) {
        roleService.editRole(request);
        return ResponseEntity.ok(null);
    }
}
