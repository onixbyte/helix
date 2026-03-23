package com.onixbyte.helix.service;

import com.onixbyte.helix.domain.database.query.wrapper.QueryAuthorityWrapper;
import com.onixbyte.helix.domain.entity.Authority;
import com.onixbyte.helix.domain.web.request.AddAuthorityRequest;
import com.onixbyte.helix.domain.web.request.EditAuthorityRequest;
import com.onixbyte.helix.domain.web.request.QueryAuthorityRequest;
import com.onixbyte.helix.enumeration.Status;
import com.onixbyte.helix.exception.BizException;
import com.onixbyte.helix.manager.AuthorityManager;
import com.onixbyte.helix.manager.RoleAuthorityManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

    public Authority addAuthority(AddAuthorityRequest request) {
        var authority = Authority.builder()
                .code(request.code())
                .name(request.name())
                .description(request.description())
                .status(Optional.ofNullable(request.status())
                        .map(Status::valueOf)
                        .orElse(Status.ACTIVE))
                .build();

        if (authorityManager.existsByCode(authority)) {
            throw new BizException(HttpStatus.CONFLICT, "权限编码 `" + authority.getCode() + "` 已被使用");
        }

        return authorityManager.save(authority);
    }

    public Authority editAuthority(EditAuthorityRequest request) {
        return authorityManager.update(request);
    }

    @Transactional(rollbackFor = Throwable.class)
    public String deleteAuthority(Long authorityId) {
        var authorityName = authorityManager.findAuthorityNameById(authorityId);

        if (StringUtils.isBlank(authorityName)) {
            throw new BizException(HttpStatus.NOT_FOUND, "Authority with ID '%d' not found.".formatted(authorityId));
        }

        roleAuthorityManager.deleteByAuthorityId(authorityId);
        authorityManager.deleteById(authorityId);

        return authorityName;
    }
}
