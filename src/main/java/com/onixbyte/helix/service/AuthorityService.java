package com.onixbyte.helix.service;

import com.onixbyte.helix.domain.entity.Authority;
import com.onixbyte.helix.domain.web.request.QueryAuthorityRequest;
import com.onixbyte.helix.manager.AuthorityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityService {

    private final AuthorityManager authorityManager;

    public AuthorityService(AuthorityManager authorityManager) {
        this.authorityManager = authorityManager;
    }

    public Page<Authority> getAuthorities(Pageable pageable, QueryAuthorityRequest request) {
        return authorityManager.selectAll(pageable);
    }
}
