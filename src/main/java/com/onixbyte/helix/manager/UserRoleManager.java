package com.onixbyte.helix.manager;

import com.onixbyte.helix.domain.entity.UserRole;
import com.onixbyte.helix.exception.BizException;
import com.onixbyte.helix.mapper.UserRoleMapper;
import com.onixbyte.helix.repository.UserRoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRoleManager {

    private static final Logger log = LoggerFactory.getLogger(UserRoleManager.class);
    private final UserRoleMapper userRoleMapper;
    private final UserRoleRepository userRoleRepository;

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
}
