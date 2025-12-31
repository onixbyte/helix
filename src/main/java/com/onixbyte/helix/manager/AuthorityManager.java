package com.onixbyte.helix.manager;

import com.onixbyte.helix.domain.database.query.wrapper.QueryAuthorityWrapper;
import com.onixbyte.helix.domain.entity.Authority;
import com.onixbyte.helix.domain.web.request.EditAuthorityRequest;
import com.onixbyte.helix.enumeration.Status;
import com.onixbyte.helix.exception.BizException;
import com.onixbyte.helix.mapper.AuthorityMapper;
import com.onixbyte.helix.repository.AuthorityRepository;
import com.onixbyte.helix.shared.CacheName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class AuthorityManager {

    private static final Logger log = LoggerFactory.getLogger(AuthorityManager.class);
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

    public Page<Authority> selectAll(Pageable pageable, QueryAuthorityWrapper wrapper) {
        return authorityRepository.findAll(pageable);
    }

    public boolean existsByCode(Authority authority) {
        return authorityRepository.exists(Example.of(Authority
                .builder()
                .code(authority.getCode())
                .build()
        ));
    }

    public Authority save(Authority authority) {
        return authorityRepository.save(authority);
    }

    @Transactional
    public Authority update(EditAuthorityRequest request) {
        var authority = authorityRepository.findById(request.id())
                .orElseThrow(() -> new BizException(HttpStatus.NOT_FOUND, "找不到指定的权限信息"));

        Optional.ofNullable(request.name())
                .ifPresent(authority::setName);

        authority.setDescription(request.description());

        Optional.ofNullable(request.status())
                .map(Status::valueOf)
                .ifPresent(authority::setStatus);

        return authority;
    }
}
