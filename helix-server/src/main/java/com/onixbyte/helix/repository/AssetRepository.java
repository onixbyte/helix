package com.onixbyte.helix.repository;

import com.onixbyte.helix.domain.entity.Asset;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Asset related database operations.
 *
 * @author zihluwang
 */
@Repository
public interface AssetRepository {

    /**
     * Save asset into database.
     *
     * @param asset an asset metadata
     * @return an asset metadata with ID
     */
    int save(@Param("asset") Asset asset);

    /**
     * Select asset by ID.
     *
     * @param assetId asset ID
     * @return asset information
     */
    Asset selectById(@Param("assetId") Long assetId);

    /**
     * Delete asset by ID.
     *
     * @param assetId asset ID
     * @return affected rows
     */
    int deleteById(@Param("assetId") Long assetId);
}
