package com.example.aftas.service.Impl;

import com.example.aftas.model.Level;
import com.example.aftas.repository.LevelRepository;
import com.example.aftas.service.LevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LevelServiceImpl implements LevelService {
    private final LevelRepository levelRepository;
    @Override
    public Level save(Level level) {
        canAddLevel(level);
        return levelRepository.save(level);
    }

    private void canAddLevel(Level level) {
        if (levelRepository.findByLevel(level.getLevel()).isPresent()) {
            throw new IllegalArgumentException("Level already exists with this code: " + level.getLevel());
        }

        Level matchingLevel = levelRepository.findFirstByPointsGreaterThanEqual(level.getPoints());
        if ( matchingLevel != null) {
            throw new IllegalArgumentException("A new level cannot be created with a point lower than or equal an existing level");
        }
    }

    @Override
    public List<Level> findAll() {
        return levelRepository.findAll();
    }



}
