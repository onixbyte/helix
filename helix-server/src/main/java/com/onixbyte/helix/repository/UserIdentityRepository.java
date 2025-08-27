package com.onixbyte.helix.repository;

import com.onixbyte.helix.domain.entity.UserIdentity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserIdentityRepository {

    int insertUserIdentity(@Param("userIdentity") UserIdentity userIdentity);
}
