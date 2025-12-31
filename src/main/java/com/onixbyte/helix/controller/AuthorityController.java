package com.onixbyte.helix.controller;

import com.onixbyte.helix.domain.entity.Authority;
import com.onixbyte.helix.domain.web.request.AddAuthorityRequest;
import com.onixbyte.helix.domain.web.request.EditAuthorityRequest;
import com.onixbyte.helix.domain.web.request.QueryAuthorityRequest;
import com.onixbyte.helix.service.AuthorityService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authorities")
public class AuthorityController {

    private final AuthorityService authorityService;

    public AuthorityController(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    @GetMapping
    public Page<Authority> getAuthorities(
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @Validated @ModelAttribute QueryAuthorityRequest request
    ) {
        var pageRequest = PageRequest.of(pageNum - 1, pageSize);
        return authorityService.getAuthorities(pageRequest, request);
    }

    @PostMapping
    public Authority addAuthority(@Validated @RequestBody AddAuthorityRequest request) {
        return authorityService.addAuthority(request);
    }

    @PutMapping
    public Authority editAuthority(@Validated @RequestBody EditAuthorityRequest request) {
        return authorityService.editAuthority(request);
    }
}
