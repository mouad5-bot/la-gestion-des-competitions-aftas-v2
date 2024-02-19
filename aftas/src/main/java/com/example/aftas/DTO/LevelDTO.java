package com.example.aftas.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


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
