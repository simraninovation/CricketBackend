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
@Table(name ="Toss")
public class Toss {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long tossId;

    @Column(name="teamIdWonToss")
    Long teamIdWonToss;

    @Column(name="battingTeamId")
    Long battingTeamId;

    @Column(name="ballingTeamId")
    Long ballingTeamId;

    @Column(name="tossOutcome")
    String tossOutcome;

    @OneToOne
    @JoinColumn(name="matchId",referencedColumnName = "matchId")
    private Matches matches;

}
