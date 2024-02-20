package com.example.aftas.dtos;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


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
