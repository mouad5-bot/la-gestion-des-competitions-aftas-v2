package com.example.aftas.repository;

import com.example.aftas.model.Hunting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HuntingRepository extends JpaRepository<Hunting, Long> {
    Optional<Hunting> findByMemberNumAndCompetitionCodeAndFishId(Long num, String code, Long id);
}
