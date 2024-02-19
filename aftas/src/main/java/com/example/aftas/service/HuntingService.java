package com.example.aftas.service;

import com.example.aftas.DTO.HuntingDTORequest;
import com.example.aftas.DTO.SaveHuntDTO;
import com.example.aftas.model.Competition;
import com.example.aftas.model.Fish;
import com.example.aftas.model.Hunting;
import com.example.aftas.model.Member;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface HuntingService {
    SaveHuntDTO add(SaveHuntDTO hunting) throws Exception;
    List<HuntingDTORequest> findAll(Pageable pageable);
}
