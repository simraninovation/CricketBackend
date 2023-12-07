package com.tekion.cricket.entity;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="Matches")
public class Matches {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     Long matchId;

    @Column(name="teamIdWonToss")
    Long teamIdWonToss ;

    @Column(name="matchOver")
    Long matchOver;

    @Column(name="umpire")
    String umpire;

    @Column(name="teamIdWhoWonMatch")
    Long  teamIdWhoWonMatch;

    @ManyToOne
    @JoinColumn(name = "teamAid",referencedColumnName = "teamId",updatable = false)
    private Team teamA;

    @OneToOne
    @JoinColumn(name ="teamBid" ,referencedColumnName = "teamId",updatable = false)
    private Team teamB;

}
