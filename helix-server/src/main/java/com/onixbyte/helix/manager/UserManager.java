package com.onixbyte.helix.manager;

import com.onixbyte.helix.domain.biz.BizUser;
import com.onixbyte.helix.domain.common.Page;
import com.onixbyte.helix.domain.entity.User;
import com.onixbyte.helix.domain.view.UserView;
import com.onixbyte.helix.repository.UserRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserManager {

    private final UserRepository userRepository;

    public UserManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public BizUser getUser(Long userId) {
        return userRepository.queryBizUserById(userId);
    }

    /**
     * Query user's password by username.
     *
     * @param username username
     * @return user's password
     */
    @Cacheable(cacheNames = "user:password", key = "#username", unless = "#result == null")
    public String getPasswordByUsername(String username) {
        return userRepository.queryPasswordByUsername(username);
    }

    public Page<UserView> queryUsersPaginated(Page<UserView> page) {
        var count = countUsers();
        var result = userRepository.queryUserList(page.calculateOffset(), page.getPageSize());
        page.setTotal(count);
        page.setRecords(result);
        return page;
    }

    public Long countUsers() {
        return userRepository.countUsers();
    }
}
