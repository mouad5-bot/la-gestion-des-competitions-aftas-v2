package com.example.aftas.service;

import com.example.aftas.model.Competition;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CompetitionService {
    Competition add(Competition competition);
    List<Competition> findAll(Pageable pageable);
}
