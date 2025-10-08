package com.onixbyte.helix.manager;

import com.onixbyte.helix.constant.CacheName;
import com.onixbyte.helix.domain.entity.Asset;
import com.onixbyte.helix.exception.BizException;
import com.onixbyte.helix.repository.AssetRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class AssetManager {

    private final AssetRepository assetRepository;

    public AssetManager(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    public Asset save(Asset asset) {
        var affectedRows = assetRepository.save(asset);
        if (affectedRows != 1) {
            throw new BizException(HttpStatus.INTERNAL_SERVER_ERROR, "Save asset failed.");
        }
        return asset;
    }

    @Cacheable(cacheNames = CacheName.ASSET, key = "#assetId")
    public Asset queryByAssetId(Long assetId) {
        return assetRepository.selectById(assetId);
    }

    public void deleteById(Long assetId) {
        var affectedRows = assetRepository.delete(assetId);
        if (affectedRows != 1) {
            throw new BizException(HttpStatus.INTERNAL_SERVER_ERROR, "Delete asset failed.");
        }
    }
}
