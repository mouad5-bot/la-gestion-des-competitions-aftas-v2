package com.example.aftas.web.rest;


import com.example.aftas.DTO.HuntingDTORequest;
import com.example.aftas.DTO.SaveHuntDTO;
import com.example.aftas.handler.response.ApiResponse;
import com.example.aftas.model.Hunting;
import com.example.aftas.service.HuntingService;
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
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hunt")
public class HuntingRest {


    private final HuntingService huntingService;
    private final ModelMapper modelMapper;
    private Logger logger = LoggerFactory.getLogger(CompetitionRest.class);

    @PostMapping(value = "add")
    public ResponseEntity<ApiResponse<SaveHuntDTO>> save(@Valid @RequestBody SaveHuntDTO saveHuntDTO){

        try {

            SaveHuntDTO hunting = huntingService.add(saveHuntDTO);

            if(hunting == null){

                return ResponseEntity.badRequest().body(
                        ApiResponse.badRequest("Hunt isn't created")
                );
            }else{

                return  ResponseEntity.ok(
                        ApiResponse.created("The Hunt is created successfully", hunting)
                );
            }

        }catch (Exception e){

            logger.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(
                    ApiResponse
                            .badRequest(e.getMessage())
            );
        }
    }

    @GetMapping("all")
    public ApiResponse<List<HuntingDTORequest>> findAll(@ParameterObject Pageable pageable){

        List<HuntingDTORequest> hunting = huntingService.findAll(pageable);

        if (hunting == null){
            return ApiResponse
                    .notFound("Not found any hunting");
        }else {
            return ApiResponse
                    .success("The hunts has retrieved successfully", hunting);
        }

    }

}
