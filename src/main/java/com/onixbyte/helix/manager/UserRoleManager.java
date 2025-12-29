package com.onixbyte.helix.manager;

import com.onixbyte.helix.domain.entity.UserRole;
import com.onixbyte.helix.domain.entity.embeddable.UserRoleId;
import com.onixbyte.helix.mapper.UserRoleMapper;
import com.onixbyte.helix.repository.UserRoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRoleManager {

    private static final Logger log = LoggerFactory.getLogger(UserRoleManager.class);
    private final UserRoleMapper userRoleMapper;
    private final UserRoleRepository userRoleRepository;

    @Autowired
    public UserRoleManager(UserRoleMapper userRoleMapper, UserRoleRepository userRoleRepository) {
        this.userRoleMapper = userRoleMapper;
        this.userRoleRepository = userRoleRepository;
    }

    public List<UserRole> saveBatch(List<UserRole> userRoles) {
        return userRoleRepository.saveAll(userRoles);
    }

    public void deleteByUserId(Long userId) {
        var affectedRows = userRoleRepository.deleteByUserId(userId);
        log.info("用户 {} 的角色关联被全部移除（共 {} 条）。", userId, affectedRows);
    }

    public void deleteByRoleId(Long roleId) {
        var affectedRows = userRoleRepository.deleteByRoleId(roleId);
        log.info("角色 {} 的用户关联被全部移除（共 {} 条）。", roleId, affectedRows);
    }
}
