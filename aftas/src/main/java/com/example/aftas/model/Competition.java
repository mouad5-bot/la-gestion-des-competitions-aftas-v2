package com.example.aftas.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Competition implements Serializable{
    @Id
    private String code;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Long numberOfParticipants;
    private String location;
    private Long amountOfFish;

    @OneToMany(mappedBy = "competition")
    @JsonManagedReference
    private List<Ranking> rankingList;

    @OneToMany(mappedBy = "competition")
    @JsonManagedReference
    private List<Hunting> huntingList;
}
