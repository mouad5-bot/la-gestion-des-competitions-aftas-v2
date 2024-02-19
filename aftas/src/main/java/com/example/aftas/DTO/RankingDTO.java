package com.example.aftas.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RankingDTO {

    private Long member_id;

    private String competition_code;

    @NotNull(message = "Rank cannot be null")
    private Long rank;

    @NotNull(message = "Score cannot be null")
    private Long score;
}
