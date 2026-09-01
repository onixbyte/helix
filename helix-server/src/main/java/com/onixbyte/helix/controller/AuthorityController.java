package com.onixbyte.helix.controller;

import com.onixbyte.helix.domain.entity.Authority;
import com.onixbyte.helix.domain.web.request.AuthorityRequest;
import com.onixbyte.helix.domain.web.request.QueryAuthorityRequest;
import com.onixbyte.helix.domain.web.response.ActionResponse;
import com.onixbyte.helix.service.AuthorityService;
import com.onixbyte.helix.shared.MessageName;
import com.onixbyte.helix.utils.MessageUtil;
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
    private final MessageUtil messageUtil;

    public AuthorityController(
            AuthorityService authorityService,
            MessageUtil messageUtil
    ) {
        this.authorityService = authorityService;
        this.messageUtil = messageUtil;
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
    public Authority addAuthority(@Validated @RequestBody AuthorityRequest request) {
        return authorityService.addAuthority(request);
    }

    /**
     * Edit an authority.
     *
     * @param request authority specs
     * @return edited authority
     */
    @PutMapping("/{id:\\d+}")
    public Authority editAuthority(
            @PathVariable Long id,
            @Validated @RequestBody AuthorityRequest request
    ) {
        return authorityService.editAuthority(id, request);
    }

    @DeleteMapping("/{authorityId:\\d+}")
    public ActionResponse deleteAuthority(@PathVariable Long authorityId) {
        var name = authorityService.deleteAuthority(authorityId);
        return ActionResponse.success(messageUtil.getMessage(MessageName.AUTHORITY_DELETED, name));
    }
}
