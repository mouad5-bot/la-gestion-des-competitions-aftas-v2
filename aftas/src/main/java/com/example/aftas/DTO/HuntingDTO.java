package com.example.aftas.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HuntingDTO {
    private Long id;

    @Positive
    @NotNull(message = "Fish number cannot be null")
    private Long numberOfFish;
}
