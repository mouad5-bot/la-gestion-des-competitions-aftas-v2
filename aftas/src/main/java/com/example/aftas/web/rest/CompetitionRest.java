package com.example.aftas.web.rest;

import com.example.aftas.dtos.CompetitionDTO;
import com.example.aftas.handler.response.ApiResponse;
import com.example.aftas.model.Competition;
import com.example.aftas.service.CompetitionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/competition")
public class CompetitionRest {

    private final CompetitionService competitionService;
    private final ModelMapper modelMapper;
    private Logger logger = LoggerFactory.getLogger(CompetitionRest.class);

    @PostMapping(value = "add")
    public ResponseEntity<ApiResponse<Competition>> save(@Valid @RequestBody CompetitionDTO competitionDTO){

        try {

            Competition competitionObject = competitionService.add(modelMapper.map(competitionDTO, Competition.class));

            if(competitionObject == null){

                return ResponseEntity.badRequest().body(
                        ApiResponse.badRequest("Competition isn't created")
                    );
            }else{

                return  ResponseEntity.ok(
                        ApiResponse.created("The competition is created successfully", competitionObject)
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
    public ApiResponse<List<Competition>> findAll(@ParameterObject Pageable pageable){

        List<Competition> competitionList = competitionService.findAll(pageable);

        if (competitionList == null){
            return ApiResponse
                    .notFound("Not found any competition");
        }else {
            return ApiResponse
                    .success("The competitions has retrieved successfully", competitionList);
        }

    }
}
