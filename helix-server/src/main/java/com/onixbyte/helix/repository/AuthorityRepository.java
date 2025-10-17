package com.onixbyte.helix.repository;

import com.onixbyte.helix.domain.entity.Authority;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorityRepository {

    /**
     * Select authorities that is granted to the specific user.
     *
     * @param userId user ID
     * @return authorities
     */
    List<Authority> selectByUserId(@Param("userId") Long userId);
}
