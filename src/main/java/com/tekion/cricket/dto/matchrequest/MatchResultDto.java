package com.tekion.cricket.dto.matchrequest;

import lombok.Data;

@Data
public class MatchResultDto {
    private Long matchId;
    private Long teamA;
    private Long teamB;
    private int matchOvers;
    private String umpire;
    private Long teamIdWonToss;
    private Long teamIdWhoWonMatch;
}
