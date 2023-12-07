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
@Table(name ="ballSummary")
public class BallSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long Id;

    @Column(name="outcomeOfBall")
    String outcomeOfBall;
    @Column(name="currentOver")
    int currentOver;
    @ManyToOne
    @JoinColumn(name="matchId",referencedColumnName = "matchId",nullable = false)
    private Matches matches;

    @ManyToOne
    @JoinColumn(name="inningId",referencedColumnName = "inningId",nullable = false)
    private Innings inning;






    //matchId integer
    //inningsId integer [primary key]

    //currentOver integer [primary key]
}
