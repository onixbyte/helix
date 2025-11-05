package com.onixbyte.helix.repository;

import com.onixbyte.helix.domain.entity.User;
import com.onixbyte.helix.domain.web.request.QueryUserRequest;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {

    /**
     * Select user by username.
     *
     * @param username username
     * @return user
     */
    User selectByUsername(@Param("username") String username);

    /**
     * Select all users paginated
     *
     * @param pageable page request
     * @return users in this page
     */
    List<User> selectAll(@Param("pageable") Pageable pageable);

    /**
     * Get user count.
     *
     * @return count of users
     */
    int countAll();

    List<User> selectList(
            @Param("pageable") Pageable pageable,
            @Param("request") QueryUserRequest request
    );

    int count(@Param("request") QueryUserRequest request);

    int save(@Param("user") User user);

    User selectById(@Param("id") Long id);

    int update(@Param("user") User user);
}
