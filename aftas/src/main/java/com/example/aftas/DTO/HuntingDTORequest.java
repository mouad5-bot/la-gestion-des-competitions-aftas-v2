package com.example.aftas.DTO;

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
