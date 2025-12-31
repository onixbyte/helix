package com.onixbyte.helix.manager;

import com.onixbyte.helix.shared.CacheName;
import com.onixbyte.helix.domain.entity.Asset;
import com.onixbyte.helix.mapper.AssetMapper;
import com.onixbyte.helix.repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class AssetManager {

    private final AssetMapper assetMapper;
    private final AssetRepository assetRepository;

    @Autowired
    public AssetManager(AssetMapper assetMapper, AssetRepository assetRepository) {
        this.assetMapper = assetMapper;
        this.assetRepository = assetRepository;
    }

    @CachePut(cacheNames = CacheName.ASSET, key = "#result.id", unless = "#result == null")
    public Asset save(Asset asset) {
        return assetRepository.save(asset);
    }

    @Cacheable(cacheNames = CacheName.ASSET, key = "#assetId")
    public Asset queryByAssetId(Long assetId) {
        return assetRepository.findById(assetId)
                .orElse(null);
    }

    @CacheEvict(cacheNames = CacheName.ASSET, key = "#assetId")
    public void deleteById(Long assetId) {
        assetRepository.deleteById(assetId);
    }
}
