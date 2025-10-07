package com.onixbyte.helix.manager;

import com.onixbyte.helix.constant.CacheName;
import com.onixbyte.helix.domain.entity.User;
import com.onixbyte.helix.repository.UserRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class UserManager {

    private final UserRepository userRepository;

    public UserManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Cacheable(cacheNames = CacheName.USER, key = "#username", unless = "#result == null")
    public User queryByUsername(String username) {
        return userRepository.selectByUsername(username);
    }
}
