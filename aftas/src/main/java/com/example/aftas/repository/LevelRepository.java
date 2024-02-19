package com.example.aftas.repository;

import com.example.aftas.model.Competition;
import com.example.aftas.model.Level;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LevelRepository extends JpaRepository<Level, Long> {
    Optional<Level> findByLevel(Long level);

    Level findFirstByPointsGreaterThanEqual(Long points);
}
