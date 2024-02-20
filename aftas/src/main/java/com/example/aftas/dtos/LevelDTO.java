package com.example.aftas.dtos;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LevelDTO {

    private Long level;

    @NotBlank(message = "Description cannot be null")
    private String description;

    @NotNull(message = "Points cannot be null")
    private Long points;

}
