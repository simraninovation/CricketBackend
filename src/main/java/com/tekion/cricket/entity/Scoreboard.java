package com.tekion.cricket.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="Scoreboard")
public class Scoreboard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long scoreboardId;
    @Column(name="zero")
    int zero;
    @Column(name="one")
    int one;
    @Column(name="two")
    int two;
    @Column(name="three")
    int three;
    @Column(name="four")
    int four;
    @Column(name="five")
    int five;

    @Column(name="six")
    int six;
    @Column(name="extraRun")
    int extraRun;

    @Column(name="wicket")
    int wicket;
    @OneToOne
    @JoinColumn(name="playerId",referencedColumnName = "Id")
    private Players players;

    @ManyToOne
    @JoinColumn(name="matchId",referencedColumnName = "matchId",nullable = false)
    private Matches matches;


}
