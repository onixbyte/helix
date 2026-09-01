package com.onixbyte.helix.service;

import com.onixbyte.helix.domain.entity.Position;
import com.onixbyte.helix.manager.PositionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PositionService {

    private final PositionManager positionManager;

    @Autowired
    public PositionService(PositionManager positionManager) {
        this.positionManager = positionManager;
    }

    public Page<Position> getPositions(Pageable pageable) {
        return positionManager.selectAll(pageable);
    }
}
