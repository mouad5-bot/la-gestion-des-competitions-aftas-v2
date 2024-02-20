package com.example.aftas.model;

import com.example.aftas.model.enums.IdentityDocumentType;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;
    private String name;
    private String familyName;
    private LocalDate accessDate;
    private String nationality;

    @Enumerated(EnumType.STRING)
    private IdentityDocumentType identityDocumentType;
    private String identityNumber;

    @OneToMany(mappedBy = "member")
    @JsonManagedReference
    private List<Ranking> rankings;

    @OneToMany(mappedBy = "member")
    @JsonManagedReference
    private List<Hunting> huntingList;
}
