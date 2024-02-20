package com.example.aftas.service;

import com.example.aftas.dtos.HuntingDTORequest;
import com.example.aftas.dtos.SaveHuntDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HuntingService {
    SaveHuntDTO add(SaveHuntDTO hunting) throws Exception;
    List<HuntingDTORequest> findAll(Pageable pageable);
}
