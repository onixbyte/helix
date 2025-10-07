package com.onixbyte.helix.repository;

import com.onixbyte.helix.domain.entity.Asset;
import jakarta.annotation.Nonnull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {

    @Override
    @Nonnull
    <S extends Asset> S save(@Nonnull S asset);

    @Override
    void deleteById(@Nonnull Long id);

    @Override
    @Nonnull
    Optional<Asset> findById(@Nonnull Long id);

    @Nonnull
    Page<Asset> findAll(@Nonnull Pageable pageable);

    @Override
    long count();

    Optional<Asset> findByKey(String key);

    Page<Asset> findByUploadBy(Long uploadBy, Pageable pageable);
}
