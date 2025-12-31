package com.onixbyte.helix.manager;

import com.onixbyte.helix.shared.CacheName;
import com.onixbyte.helix.domain.entity.Authority;
import com.onixbyte.helix.mapper.AuthorityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthorityManager {

    private final AuthorityMapper authorityMapper;

    @Autowired
    public AuthorityManager(AuthorityMapper authorityMapper) {
        this.authorityMapper = authorityMapper;
    }

    @Cacheable(cacheNames = CacheName.AUTHORITIES_OF_USER, key = "#userId")
    public List<Authority> queryByUserId(Long userId) {
        return authorityMapper.selectByUserId(userId);
    }
}
