package com.tekion.cricket.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="scheduleMatch")
public class ScheduledMatch {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int scheduleMatchId;

    @Column(name="venue")
    String venue;

    @Column(name="date")
    Date date;

    @Column(name="matchStatus")
    String matchStatus;

    @Column(name = "teamA",nullable = false)
    private Long teamA;

    @Column(name = "teamB",nullable = false)
    private Long teamB;

    @OneToOne
    @JoinColumn(name="matchId",referencedColumnName = "matchId",nullable = false)
    private Matches matches;
}
