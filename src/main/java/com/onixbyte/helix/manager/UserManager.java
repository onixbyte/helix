/*
 * Copyright (c) 2024-2026 OnixByte
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.onixbyte.helix.manager;

import com.onixbyte.helix.common.regex.Patterns;
import com.onixbyte.helix.domain.web.request.ResetPasswordRequest;
import com.onixbyte.helix.mapper.UserCredentialMapper;
import com.onixbyte.helix.shared.CacheName;
import com.onixbyte.helix.domain.database.query.wrapper.QueryUserWrapper;
import com.onixbyte.helix.domain.entity.User;
import com.onixbyte.helix.domain.web.response.UserDetailResponse;
import com.onixbyte.helix.exception.BizException;
import com.onixbyte.helix.mapper.UserMapper;
import com.onixbyte.helix.repository.UserRepository;
import com.onixbyte.region.Region;
import com.onixbyte.helix.shared.MessageName;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class UserManager {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserCredentialMapper userCredentialMapper;

    @Autowired
    public UserManager(
            UserMapper userMapper,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            UserCredentialMapper userCredentialMapper
    ) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userCredentialMapper = userCredentialMapper;
    }

    /**
     * Get user by username, and cache this user by username.
     *
     * @param username username
     * @return user
     */
    @Cacheable(cacheNames = CacheName.USER, key = "#username", unless = "#result == null")
    public User selectByUsername(String username) {
        return userRepository.findOne(Example.of(User.builder()
                        .username(username)
                        .build()))
                .orElse(null);
    }

    /**
     * Query paginated users.
     *
     * @param pageable page request
     * @return page result
     */
    public Page<UserDetailResponse> selectUserDetailsPage(Pageable pageable, QueryUserWrapper wrapper) {
        var result = userMapper.selectListWithDetails(pageable, wrapper);
        var total = userMapper.count(wrapper);
        return new PageImpl<>(result, pageable, total);
    }

    @CachePut(cacheNames = CacheName.USER, key = "#user.username")
    public User save(User user) {
        return userRepository.save(user);
    }

    public UserDetailResponse queryUserDetailByUserId(Long userId) {
        return userMapper.selectWithDetailByUserId(userId);
    }

    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }

    @CachePut(cacheNames = CacheName.USER, key = "#result.username", unless = "#result == null")
    @Transactional(rollbackFor = Throwable.class)
    public User updateUser(User user) {
        var userToUpdate = userRepository.findById(user.getId())
                .orElseThrow(() -> new BizException(HttpStatus.BAD_REQUEST, MessageName.USER_NOT_FOUND, user.getId()));

        Optional.ofNullable(user.getFullName())
                .filter(StringUtils::isNotBlank)
                .ifPresent(userToUpdate::setFullName);

        Optional.ofNullable(user.getEmail())
                .filter(StringUtils::isNotBlank)
                .filter((email) -> Patterns.EMAIL.asPredicate().test(email))
                .ifPresent(userToUpdate::setEmail);

        Optional.ofNullable(user.getRegionAbbreviation())
                .filter(StringUtils::isNotBlank)
                .filter(Region::isValidAbbreviation)
                .ifPresent(userToUpdate::setRegionAbbreviation);

        Optional.ofNullable(user.getPhoneNumber())
                .filter(StringUtils::isNotBlank)
                .ifPresent(userToUpdate::setPhoneNumber);

        Optional.ofNullable(user.getAvatarUrl())
                .filter(StringUtils::isNotBlank)
                .filter((avatarUrl) -> Patterns.IMAGE_URL.asPredicate().test(avatarUrl) || Patterns.GRAVATAR_IMAGE_URL.asPredicate().test(avatarUrl))
                .ifPresent(userToUpdate::setAvatarUrl);

        Optional.ofNullable(user.getStatus())
                .ifPresent(userToUpdate::setStatus);

        Optional.ofNullable(user.getDepartmentId())
                .ifPresent(userToUpdate::setDepartmentId);

        Optional.ofNullable(user.getPositionId())
                .ifPresent(userToUpdate::setPositionId);

        return userToUpdate;
    }

    @Transactional(rollbackFor = Throwable.class)
    public void updatePasswordById(Long id, ResetPasswordRequest request) {
        userCredentialMapper.updateUserCredential(
                id,
                passwordEncoder.encode(request.password())
        );
    }
}
