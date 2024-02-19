package com.example.aftas.web.rest;

import com.example.aftas.DTO.FishDTO;
import com.example.aftas.handler.response.ApiResponse;
import com.example.aftas.model.Competition;
import com.example.aftas.model.Fish;
import com.example.aftas.service.FishService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/fish")
public class FishRest {
    private final FishService fishService;
    private final ModelMapper modelMapper;
    private Logger logger = LoggerFactory.getLogger(CompetitionRest.class);
    @PostMapping(value = "add")
    public ResponseEntity<ApiResponse<Fish>> save(@Valid @RequestBody FishDTO fishDTO){

        try {

            Fish fish = fishService.add( modelMapper.map(fishDTO, Fish.class));

            if(fish == null){

                return ResponseEntity.badRequest().body(
                        ApiResponse.badRequest("fish isn't created")
                );
            }else{

                return  ResponseEntity.ok(
                        ApiResponse.created("The fish is created successfully", fish)
                );
            }

        }catch (IllegalArgumentException e){

            logger.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(
                    ApiResponse
                            .badRequest(e.getMessage())
            );
        }
    }

    @GetMapping("all")
    public ApiResponse<List<FishDTO>> findAll(@ParameterObject Pageable pageable){

        List<FishDTO> fishList = fishService.findAll(pageable);

        if (fishList == null){
            return ApiResponse
                    .notFound("Not found any competition");
        }else {
            return ApiResponse
                    .success("The competitions has retrieved successfully", fishList);
        }

    }

}
