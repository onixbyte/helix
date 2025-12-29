package com.onixbyte.helix.manager;

import com.onixbyte.helix.domain.database.query.wrapper.QueryRoleWrapper;
import com.onixbyte.helix.domain.entity.Role;
import com.onixbyte.helix.exception.BizException;
import com.onixbyte.helix.mapper.RoleAuthorityMapper;
import com.onixbyte.helix.mapper.RoleMapper;
import com.onixbyte.helix.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class RoleManager {

    private final RoleMapper roleMapper;
    private final RoleRepository roleRepository;
    private final RoleAuthorityMapper roleAuthorityMapper;

    @Autowired
    public RoleManager(RoleMapper roleMapper, RoleRepository roleRepository, RoleAuthorityMapper roleAuthorityMapper) {
        this.roleMapper = roleMapper;
        this.roleRepository = roleRepository;
        this.roleAuthorityMapper = roleAuthorityMapper;
    }

    public void validateRoles(List<Long> roleIds) {
        if (!roleMapper.areRolesExisted(roleIds)) {
            throw new BizException(HttpStatus.BAD_REQUEST, "Role does not exist in database.");
        }
    }

    public List<Role> getRoles(Role example) {
        return roleRepository.findAll(Example.of(example), Sort.by(Sort.Order.asc("id")));
    }

    public Page<Role> selectAll(Pageable pageable, QueryRoleWrapper wrapper) {
        var records = roleMapper.selectAll(pageable, wrapper);
        var total = roleMapper.count(wrapper);

        return new PageImpl<>(records, pageable, total);
    }

    public Role save(Role role) {
        return roleRepository.save(role);
    }

    public Optional<Role> getRoleById(Long id) {
        return roleRepository.findById(id);
    }

    @Transactional
    public void updateRole(Role role) {
        var roleToUpdate = roleRepository.findById(role.getId())
                .orElseThrow(() -> new BizException(HttpStatus.NOT_FOUND, "找不到指定的角色信息。"));

        Optional.ofNullable(role.getName())
                .ifPresent(roleToUpdate::setName);

        Optional.ofNullable(role.getCode())
                .ifPresent(roleToUpdate::setCode);

        Optional.ofNullable(role.getSort())
                .ifPresent(roleToUpdate::setSort);

        roleToUpdate.setDescription(role.getDescription());

        Optional.ofNullable(role.getStatus())
                .ifPresent(roleToUpdate::setStatus);
    }

    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
}
