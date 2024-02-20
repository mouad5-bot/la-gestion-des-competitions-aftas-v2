package com.example.aftas.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HuntingDTORequest {

    private String memberName;
    private String competitionCode;
    private Long numberOfFish;
}
