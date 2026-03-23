package com.onixbyte.helix.manager;

import com.onixbyte.helix.domain.entity.Position;
import com.onixbyte.helix.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class PositionManager {

    private final PositionRepository positionRepository;

    @Autowired
    public PositionManager(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    public Page<Position> selectAll(Pageable pageable) {
        return positionRepository.findAll(pageable);
    }

    public Position selectById(Long id) {
        return positionRepository.findById(id).orElse(null);
    }
}
