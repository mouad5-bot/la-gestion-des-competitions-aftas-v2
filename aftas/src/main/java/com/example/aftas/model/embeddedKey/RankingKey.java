package com.example.aftas.model.embeddedKey;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
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
