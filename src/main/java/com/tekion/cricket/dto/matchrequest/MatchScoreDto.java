package com.tekion.cricket.dto.matchrequest;

import lombok.*;

@Data
public class MatchScoreDto {
    private int wicket;

    private int extraRun;

    private int totalScores;

    private int runs;

    private int overTillNow;

    private int totalWickets;
    public MatchScoreDto(){
        this.wicket = 0;
        this.extraRun = 0;
        this.totalScores = 0;
        this.runs = 0;
        this.overTillNow = 0;
        this.totalWickets = 0;

    }
}
