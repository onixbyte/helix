package com.onixbyte.helix.repository;

import com.onixbyte.helix.domain.entity.Authority;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorityRepository {

    List<Authority> queryAuthorities(@Param("roleId") Long roleId);
}
