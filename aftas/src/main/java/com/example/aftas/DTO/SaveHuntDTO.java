package com.example.aftas.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

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
