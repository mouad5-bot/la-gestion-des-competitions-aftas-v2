package com.example.aftas.model;

import com.example.aftas.model.embeddedKey.RankingKey;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ranking {
    @EmbeddedId
    private RankingKey id;
    private Long rank;
    private Long score;

    @ManyToOne
    @MapsId("num")
    @JsonBackReference
    private Member member;

    @ManyToOne
    @MapsId("code")
    @JsonBackReference
    private Competition competition;
}
