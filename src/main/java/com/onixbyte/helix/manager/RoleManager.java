package com.onixbyte.helix.manager;

import com.onixbyte.helix.domain.database.query.wrapper.QueryRoleWrapper;
import com.onixbyte.helix.domain.entity.Role;
import com.onixbyte.helix.exception.BizException;
import com.onixbyte.helix.mapper.RoleMapper;
import com.onixbyte.helix.repository.RoleRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RoleManager {

    private final RoleMapper roleMapper;
    private final RoleRepository roleRepository;

    public RoleManager(RoleMapper roleMapper, RoleRepository roleRepository) {
        this.roleMapper = roleMapper;
        this.roleRepository = roleRepository;
    }

    public void validateRoles(List<Long> roleIds) {
        if (!roleMapper.areRolesExisted(roleIds)) {
            throw new BizException(HttpStatus.BAD_REQUEST, "Role does not exist in database.");
        }
    }

    public Optional<Role> getRole(Role example) {
        return roleRepository.findOne(Example.of(example));
    }

    public Page<Role> selectAll(Pageable pageable, QueryRoleWrapper wrapper) {
        var records = roleMapper.selectAll(pageable, wrapper);
        var total = roleMapper.count(wrapper);

        return new PageImpl<>(records, pageable, total);
    }

    public Role save(Role role) {
        return roleRepository.save(role);
    }
}
