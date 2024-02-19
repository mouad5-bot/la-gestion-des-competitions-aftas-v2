package com.example.aftas.DTO;

import com.example.aftas.model.Competition;
import com.example.aftas.model.Hunting;
import com.example.aftas.model.Ranking;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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