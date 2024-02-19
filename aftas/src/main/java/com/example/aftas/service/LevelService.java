package com.example.aftas.service;

import com.example.aftas.model.Level;

import java.util.List;

public interface LevelService {

    Level save(Level level);
    List<Level> findAll();
}
