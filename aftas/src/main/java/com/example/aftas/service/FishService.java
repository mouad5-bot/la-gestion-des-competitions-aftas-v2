package com.example.aftas.service;

import com.example.aftas.DTO.FishDTO;
import com.example.aftas.model.Competition;
import com.example.aftas.model.Fish;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FishService {

    Fish add(Fish fish);
    List<FishDTO> findAll(Pageable pageable);
}
