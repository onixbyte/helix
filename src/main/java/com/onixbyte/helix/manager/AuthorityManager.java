package com.onixbyte.helix.manager;

import com.onixbyte.helix.repository.AuthorityRepository;
import com.onixbyte.helix.shared.CacheName;
import com.onixbyte.helix.domain.entity.Authority;
import com.onixbyte.helix.mapper.AuthorityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthorityManager {

    private final AuthorityMapper authorityMapper;
    private final AuthorityRepository authorityRepository;

    @Autowired
    public AuthorityManager(AuthorityMapper authorityMapper, AuthorityRepository authorityRepository) {
        this.authorityMapper = authorityMapper;
        this.authorityRepository = authorityRepository;
    }

    @Cacheable(cacheNames = CacheName.AUTHORITIES_OF_USER, key = "#userId")
    public List<Authority> queryByUserId(Long userId) {
        return authorityMapper.selectByUserId(userId);
    }

    public Page<Authority> selectAll(Pageable pageable) {
        return authorityRepository.findAll(pageable);
    }
}
