package com.onixbyte.helix.repository;

import com.onixbyte.helix.domain.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    @Query("""
           select a.name
           from Authority a
           where a.id = :authorityId
           """)
    String findAuthorityNameById(Long authorityId);
}
