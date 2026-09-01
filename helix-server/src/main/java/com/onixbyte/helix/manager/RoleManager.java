package com.onixbyte.helix.manager;

import com.onixbyte.helix.domain.database.query.wrapper.QueryRoleWrapper;
import com.onixbyte.helix.domain.entity.Role;
import com.onixbyte.helix.exception.BizException;
import com.onixbyte.helix.mapper.RoleMapper;
import com.onixbyte.helix.repository.RoleRepository;
import com.onixbyte.helix.shared.MessageName;
import com.onixbyte.helix.utils.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class RoleManager {

    private final RoleMapper roleMapper;
    private final RoleRepository roleRepository;
    private final MessageUtil messageUtil;

    @Autowired
    public RoleManager(RoleMapper roleMapper, RoleRepository roleRepository, MessageUtil messageUtil) {
        this.roleMapper = roleMapper;
        this.roleRepository = roleRepository;
        this.messageUtil = messageUtil;
    }

    public void validateRoles(List<Long> roleIds) {
        if (!roleMapper.areRolesExisted(roleIds)) {
            throw new BizException(HttpStatus.BAD_REQUEST, MessageName.ROLE_NOT_EXISTS);
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
    public Role fullUpdateById(Long id, Role role) {
        var updatedAt = LocalDateTime.now();

        var roleToUpdate = roleRepository.findById(id)
                .orElseThrow(() -> new BizException(
                        HttpStatus.NOT_FOUND,
                        messageUtil.getMessage(MessageName.ROLE_NOT_FOUND, id))
                );

        roleToUpdate.setName(role.getName());
        roleToUpdate.setCode(role.getCode());
        roleToUpdate.setSort(role.getSort());
        roleToUpdate.setDefaultValue(role.getDefaultValue());
        roleToUpdate.setDescription(role.getDescription());
        roleToUpdate.setStatus(role.getStatus());
        roleToUpdate.setUpdatedAt(updatedAt);

        return role;
    }

    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
}
