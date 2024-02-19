package com.example.aftas.repository;

import com.example.aftas.model.Competition;
import com.example.aftas.model.Ranking;
import com.example.aftas.model.embeddedKey.RankingKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, RankingKey> {

}
