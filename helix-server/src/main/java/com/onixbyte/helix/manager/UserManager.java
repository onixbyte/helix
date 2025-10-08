package com.onixbyte.helix.manager;

import com.onixbyte.helix.constant.CacheName;
import com.onixbyte.helix.domain.entity.User;
import com.onixbyte.helix.repository.UserRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class UserManager {

    private final UserRepository userRepository;

    public UserManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Get user by username, and cache this user by username.
     *
     * @param username username
     * @return user
     */
    @Cacheable(cacheNames = CacheName.USER, key = "#username", unless = "#result == null")
    public User queryByUsername(String username) {
        return userRepository.selectByUsername(username);
    }

    /**
     * Query paginated users.
     *
     * @param pageable page request
     * @return page result
     */
    public Page<User> queryPage(Pageable pageable) {
        var result = userRepository.selectAll(pageable);
        var total = userRepository.countAll();
        return new PageImpl<>(result, pageable, total)
                .map((user) -> {
                    user.setPassword(null);
                    return user;
                });
    }
}
