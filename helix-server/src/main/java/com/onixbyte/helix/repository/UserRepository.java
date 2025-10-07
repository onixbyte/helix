package com.onixbyte.helix.repository;

import com.onixbyte.helix.domain.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {

    User selectByUsername(@Param("username") String username);
}
