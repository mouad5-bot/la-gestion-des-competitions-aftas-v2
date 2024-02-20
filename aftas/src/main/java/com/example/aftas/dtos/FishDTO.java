package com.example.aftas.dtos;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FishDTO {

    private Long id;
    @NotBlank(message = "Name cannot be null")
    private String name;
    @NotNull(message = "average Weight  cannot be null")
    private double averageWeight;

}
