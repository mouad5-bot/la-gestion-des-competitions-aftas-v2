package com.example.aftas.service;

import com.example.aftas.dtos.RankingDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RankingService {
    RankingDTO add(RankingDTO ranking) throws Exception;
    List<RankingDTO> getAll(Pageable pageable);
}
