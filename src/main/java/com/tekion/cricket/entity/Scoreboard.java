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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="totalRuns")
    private Integer totalRuns;

    @Column(name="totalWickets")
    private Integer totalWickets;

    @Column(name="oversBowled")
    private Integer oversBowled;

    @Column(name="result")
    private String result;

    @Column(name="matchId")
    private Long matchId;

    @OneToOne
    @JoinColumn(name="inningId",referencedColumnName = "inningId",nullable = false)
    private Innings innings;
}
