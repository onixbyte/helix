package com.onixbyte.helix.service;

import com.onixbyte.helix.domain.database.query.wrapper.QueryAuthorityWrapper;
import com.onixbyte.helix.domain.entity.Authority;
import com.onixbyte.helix.domain.web.request.AuthorityRequest;
import com.onixbyte.helix.domain.web.request.QueryAuthorityRequest;
import com.onixbyte.helix.exception.BizException;
import com.onixbyte.helix.manager.AuthorityManager;
import com.onixbyte.helix.manager.RoleAuthorityManager;
import com.onixbyte.helix.shared.MessageName;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorityService {

    private final AuthorityManager authorityManager;
    private final RoleAuthorityManager roleAuthorityManager;

    public AuthorityService(AuthorityManager authorityManager, RoleAuthorityManager roleAuthorityManager) {
        this.authorityManager = authorityManager;
        this.roleAuthorityManager = roleAuthorityManager;
    }

    public Page<Authority> getAuthorities(Pageable pageable, QueryAuthorityRequest request) {
        var wrapper = new QueryAuthorityWrapper();

        return authorityManager.selectAll(pageable, wrapper);
    }

    public Authority addAuthority(AuthorityRequest request) {
        var authority = Authority.builder()
                .code(request.code())
                .name(request.name())
                .description(request.description())
                .status(request.status())
                .build();

        if (authorityManager.existsByCode(authority)) {
            throw new BizException(HttpStatus.CONFLICT, MessageName.AUTHORITY_CODE_USED, authority.getCode());
        }

        return authorityManager.save(authority);
    }

    public Authority editAuthority(Long id, AuthorityRequest request) {
        return authorityManager.fullUpdateById(id, Authority.builder()
                .name(request.name())
                .description(request.description())
                .status(request.status())
                .build());
    }

    @Transactional(rollbackFor = Throwable.class)
    public String deleteAuthority(Long authorityId) {
        var authorityName = authorityManager.findAuthorityNameById(authorityId);

        if (StringUtils.isBlank(authorityName)) {
            throw new BizException(HttpStatus.NOT_FOUND, MessageName.AUTHORITY_NOT_FOUND, authorityId);
        }

        roleAuthorityManager.deleteByAuthorityId(authorityId);
        authorityManager.deleteById(authorityId);

        return authorityName;
    }
}
