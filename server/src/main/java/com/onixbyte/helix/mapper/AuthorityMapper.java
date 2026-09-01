package com.onixbyte.helix.mapper;

import com.onixbyte.helix.domain.entity.Authority;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AuthorityMapper {

    /**
     * Select authorities that is granted to the specific user.
     *
     * @param userId user ID
     * @return authorities
     */
    List<Authority> selectByUserId(@Param("userId") Long userId);
}
