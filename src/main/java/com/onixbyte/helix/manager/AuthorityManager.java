package com.onixbyte.helix.manager;

import com.onixbyte.helix.constant.CacheName;
import com.onixbyte.helix.domain.entity.Authority;
import com.onixbyte.helix.mapper.AuthorityMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthorityManager {

    private final AuthorityMapper authorityMapper;

    public AuthorityManager(AuthorityMapper authorityMapper) {
        this.authorityMapper = authorityMapper;
    }

    @Cacheable(cacheNames = CacheName.AUTHORITIES_OF_USER, key = "#userId")
    public List<Authority> queryByUserId(Long userId) {
        return authorityMapper.selectByUserId(userId);
    }
}
