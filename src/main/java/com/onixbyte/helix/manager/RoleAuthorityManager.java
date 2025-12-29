package com.onixbyte.helix.manager;

import com.onixbyte.helix.repository.RoleAuthorityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class RoleAuthorityManager {

    private static final Logger log = LoggerFactory.getLogger(RoleAuthorityManager.class);
    private final RoleAuthorityRepository roleAuthorityRepository;

    public RoleAuthorityManager(RoleAuthorityRepository roleAuthorityRepository) {
        this.roleAuthorityRepository = roleAuthorityRepository;
    }

    public void deleteByRoleId(Long roleId) {
        var affectedRows = roleAuthorityRepository.deleteByRoleId(roleId);
        log.info("角色 {} 关联的权限绑定已全部移除（共 {} 条）", roleId, affectedRows);
    }
}
