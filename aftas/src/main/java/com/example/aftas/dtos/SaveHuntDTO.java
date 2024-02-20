package com.example.aftas.dtos;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaveHuntDTO {

    @NotNull(message = "Fish weight is required")
    @Positive(message = "Fish weight must be positive")
    private Double fishWeight;

    @NotNull(message = "Fish is required")
    private Long fishId;

    @NotNull(message = "Member is required")
    private Long memberNum;

    @NotEmpty(message = "competition is required")
    private String competitionCode;
}
