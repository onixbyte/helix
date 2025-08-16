package com.onixbyte.onixboot.repository;

import com.onixbyte.onixboot.entities.UserIdentity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserIdentityRepository {

    int insert(@Param("userIdentity") UserIdentity userIdentity);
}
