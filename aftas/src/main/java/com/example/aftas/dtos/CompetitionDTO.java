package com.example.aftas.dtos;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompetitionDTO {

    private String code;
    @NotNull(message = "Date of competition cannot be null")
    private LocalDate date;

    @NotNull(message = "Start time cannot be null")
    private LocalTime startTime;

    @NotNull(message = "End time cannot be null")
    private LocalTime endTime;

    @NotNull(message = "Participants number cannot be null")
    private Long numberOfParticipants;

    @NotBlank(message = "Location cannot be null")
    private String location;

    @NotNull(message = "Amount of fish cannot be null")
    private Long amountOfFish;
}