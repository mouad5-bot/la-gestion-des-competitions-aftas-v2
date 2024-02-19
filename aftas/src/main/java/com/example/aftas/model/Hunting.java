package com.example.aftas.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hunting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;
    private Long numberOfFish;

    @ManyToOne
    @JsonBackReference
    private Competition competition;

    @ManyToOne
    @JsonBackReference
    private Member member;

    @ManyToOne
    @JsonBackReference
    private Fish fish;

}
