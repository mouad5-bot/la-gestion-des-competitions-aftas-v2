package com.example.aftas.DTO.mapper;

import com.example.aftas.DTO.RankingDTO;
import com.example.aftas.model.Ranking;

public class RankingMapper {

    public static RankingDTO toDto(Ranking ranking){
        return RankingDTO.builder()
                .member_id(ranking.getId().getNum())
                .competition_code(ranking.getId().getCode())
                .rank(ranking.getRank())
                .score(ranking.getScore())
                .build();
    }
}
