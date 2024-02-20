package com.example.aftas.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Fish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private double averageWeight;

    @OneToMany(mappedBy = "fish")
    @JsonManagedReference
    private List<Hunting> huntings;

    @ManyToOne
    @JsonBackReference
    private Level level;
 }
