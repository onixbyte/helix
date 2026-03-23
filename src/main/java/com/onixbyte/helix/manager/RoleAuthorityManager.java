package com.onixbyte.helix.manager;

import com.onixbyte.helix.mapper.RoleAuthorityMapper;
import com.onixbyte.helix.repository.RoleAuthorityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class RoleAuthorityManager {

    private static final Logger log = LoggerFactory.getLogger(RoleAuthorityManager.class);
    private final RoleAuthorityRepository roleAuthorityRepository;
    private final RoleAuthorityMapper roleAuthorityMapper;

    public RoleAuthorityManager(RoleAuthorityRepository roleAuthorityRepository, RoleAuthorityMapper roleAuthorityMapper) {
        this.roleAuthorityRepository = roleAuthorityRepository;
        this.roleAuthorityMapper = roleAuthorityMapper;
    }

    public void deleteByRoleId(Long roleId) {
        var affectedRows = roleAuthorityMapper.deleteByRoleId(roleId);
        log.info("A total of {} authorities linked to Role ID: {} have been successfully cleared.",
                affectedRows, roleId);
    }

    public void deleteByAuthorityId(Long authorityId) {
        var affectedRows = roleAuthorityMapper.deleteByAuthorityId(authorityId);
        log.info("The binding between {} authorities and the role has been cleared.", affectedRows);
    }
}
