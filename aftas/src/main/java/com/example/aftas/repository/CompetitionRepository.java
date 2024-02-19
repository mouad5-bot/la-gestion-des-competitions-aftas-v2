package com.example.aftas.repository;

import com.example.aftas.model.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Optional;

public interface CompetitionRepository extends JpaRepository<Competition, Long> {
    Optional<Competition> findByDate(LocalDate date);
    Optional<Competition> findByCode(String code);
    Competition findCompetitionByCode(String code);
}
