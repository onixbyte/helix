package com.onixbyte.helix.manager;

import com.onixbyte.helix.domain.entity.Asset;
import com.onixbyte.helix.repository.AssetRepository;
import org.springframework.stereotype.Component;

@Component
public class AssetManager {

    private final AssetRepository assetRepository;

    public AssetManager(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    public Asset save(Asset asset) {
        return assetRepository.save(asset);
    }
}
