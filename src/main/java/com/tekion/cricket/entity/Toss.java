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
    boolean tossOutcome;
    @OneToOne
    @JoinColumn(name="matchId",referencedColumnName = "matchId")
    private Matches matches;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Toss toss = (Toss) o;
        return teamIdWonToss == toss.teamIdWonToss && battingTeamId == toss.battingTeamId && ballingTeamId == toss.ballingTeamId && tossOutcome == toss.tossOutcome && Objects.equals(tossId, toss.tossId) && Objects.equals(matches, toss.matches);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tossId, teamIdWonToss, battingTeamId, ballingTeamId, tossOutcome, matches);
    }

    @Override
    public String toString() {
        return "Toss{" +
                "tossId=" + tossId +
                ", teamIdWonToss=" + teamIdWonToss +
                ", battingTeamId=" + battingTeamId +
                ", ballingTeamId=" + ballingTeamId +
                ", tossOutcome=" + tossOutcome +
                ", matches=" + matches +
                '}';
    }
}
