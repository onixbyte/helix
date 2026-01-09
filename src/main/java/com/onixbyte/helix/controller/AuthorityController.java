package com.onixbyte.helix.controller;

import com.onixbyte.helix.domain.entity.Authority;
import com.onixbyte.helix.domain.web.request.AddAuthorityRequest;
import com.onixbyte.helix.domain.web.request.EditAuthorityRequest;
import com.onixbyte.helix.domain.web.request.QueryAuthorityRequest;
import com.onixbyte.helix.service.AuthorityService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * This controller provides entry points for manipulate authorities.
 *
 * @author zihluwang
 * @author siujamo
 */
@RestController
@RequestMapping("/authorities")
public class AuthorityController {

    private final AuthorityService authorityService;

    public AuthorityController(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    /**
     * Get authorities by page.
     *
     * @param pageNum  current page num
     * @param pageSize page size
     * @param request  query parameters
     * @return a page contains authority data of the specified page
     */
    @GetMapping
    public Page<Authority> getAuthorities(
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @Validated @ModelAttribute QueryAuthorityRequest request
    ) {
        var pageRequest = PageRequest.of(pageNum - 1, pageSize);
        return authorityService.getAuthorities(pageRequest, request);
    }

    /**
     * Add an authority.
     *
     * @param request authority specs
     * @return created authority
     */
    @PostMapping
    public Authority addAuthority(@Validated @RequestBody AddAuthorityRequest request) {
        return authorityService.addAuthority(request);
    }

    /**
     * Edit an authority.
     *
     * @param request authority specs
     * @return edited authority
     */
    @PutMapping
    public Authority editAuthority(@Validated @RequestBody EditAuthorityRequest request) {
        return authorityService.editAuthority(request);
    }
}
