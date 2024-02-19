package com.example.aftas.service.Impl;

import com.example.aftas.DTO.FishDTO;
import com.example.aftas.model.Fish;
import com.example.aftas.repository.FishRepository;
import com.example.aftas.service.FishService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FishServiceImpl implements FishService {
    private final FishRepository fishRepository;
    private final ModelMapper modelMapper;

    @Override
    public Fish add(Fish fish) {
        if (fish == null){
            throw new IllegalArgumentException("you save a null fish !!");
        }
        return fishRepository.save(fish);
    }

    @Override
    public List<FishDTO> findAll(Pageable pageable) {
        List<Fish> fishList = fishRepository.findAll(pageable).stream().toList();
        return fishList.stream()
                .map((element) -> modelMapper.map(element, FishDTO.class))
                .collect(Collectors.toList());
    }
}
