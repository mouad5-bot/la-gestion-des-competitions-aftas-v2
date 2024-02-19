package com.example.aftas.model.embeddedKey;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RankingKey implements Serializable {
    @Column(name = "competition_code")
    private String code;

    @Column(name = "member_num")
    private Long num;
}
