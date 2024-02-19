package com.example.aftas.web.rest;

import com.example.aftas.DTO.MemberDTO;
import com.example.aftas.DTO.RankingDTO;
import com.example.aftas.handler.response.ApiResponse;
import com.example.aftas.model.Member;
import com.example.aftas.model.Ranking;
import com.example.aftas.service.RankingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ranking")
public class RankingRest {
    private final RankingService rankingService;
    private Logger logger = LoggerFactory.getLogger(RankingRest.class);

    @PostMapping("add")
    public ResponseEntity<ApiResponse<RankingDTO>> save(@Valid @RequestBody RankingDTO ranking){

        try {

            RankingDTO rankingObject = rankingService.add(ranking);

            if(rankingObject == null){

                return ResponseEntity.badRequest().body(
                        ApiResponse.badRequest("Ranking - participation in competition isn't created")
                );
            }else{

                return  ResponseEntity.ok(
                        ApiResponse.created("The Ranking is created successfully", rankingObject)
                );
            }

        }catch (IllegalArgumentException e){

            logger.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(
                    ApiResponse
                            .badRequest(e.getMessage())
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/all")
    public ApiResponse<List<RankingDTO>> getAll(@ParameterObject Pageable pageable){
        List<RankingDTO> rankingDTOList = rankingService.getAll(pageable);
        return ApiResponse
                .success("the ranking is retrieved successfully !", rankingDTOList);
    }
}
