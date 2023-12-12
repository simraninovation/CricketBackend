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
public class Innings {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long inningId;

    @Column(name="battingTeamId")
     Long battingTeamId;

    @Column(name="ballingTeamId")
    Long ballingTeamId;

    @Column(name="totalScore")
    Long totalScore;

    @Column(name="wicketsFallen")
    Long wicketsFallen;

    @Column(name="extraRuns")
    Long extraRuns;

    @Column(name="noOfNoBalls ")
    Long noOfNoBalls ;

    @Column(name="noOfWideBalls")
    Long noOfWideBalls;

    @Column(name="innStatus")
    Long innStatus;

    @ManyToOne
    @JoinColumn(name="matchId",referencedColumnName = "matchId",nullable = false)
    private Matches matches;

}
