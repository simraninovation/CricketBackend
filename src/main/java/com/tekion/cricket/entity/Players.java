package com.tekion.cricket.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="Players")
public class Players {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long Id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "Age")
    private int age;

    @Column(name = "isCaptain")
    private Boolean isCaptain;

    @Column(name ="PlayerType")
    private String playerType;

    @ManyToOne
    @JoinColumn(name="teamId", nullable = false)
    private Team team;

}
