package com.onixbyte.helix.manager;

import com.onixbyte.helix.domain.database.query.wrapper.QueryAuthorityWrapper;
import com.onixbyte.helix.domain.entity.Authority;
import com.onixbyte.helix.domain.web.request.AuthorityRequest;
import com.onixbyte.helix.enumeration.Status;
import com.onixbyte.helix.exception.BizException;
import com.onixbyte.helix.mapper.AuthorityMapper;
import com.onixbyte.helix.repository.AuthorityRepository;
import com.onixbyte.helix.shared.CacheName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    /**
     * Fully updates an existing authority by ID.
     * <p>
     * The method loads the target authority, replaces mutable fields ({@code name},
     * {@code description}, {@code status}), and refreshes {@code updatedAt} to the current time.
     * The update runs within a transactional context.
     *
     * @param id        the ID of the authority to update
     * @param authority the source data carrying new field values
     * @return the supplied {@link Authority} object
     * @throws BizException if the target authority does not exist
     */
    @Transactional
    public Authority fullUpdateById(Long id, Authority authority) {
        var updatedAt = LocalDateTime.now();

        var authorityToUpdate = authorityRepository.findById(id)
                .orElseThrow(() -> new BizException(HttpStatus.NOT_FOUND, "找不到指定的权限信息"));

        authorityToUpdate.setName(authority.getName());
        authorityToUpdate.setDescription(authority.getDescription());
        authorityToUpdate.setStatus(authority.getStatus());
        authorityToUpdate.setUpdatedAt(updatedAt);

        return authority;
    }

    public String findAuthorityNameById(Long authorityId) {
        return authorityRepository.findAuthorityNameById(authorityId);
    }

    public void deleteById(Long authorityId) {
        authorityRepository.deleteById(authorityId);
    }
}
