package com.tekion.cricket.dto.matchrequest;

import lombok.Data;

@Data
public class NewMatchDto {
    private Long matchId;
    private Long teamA;
    private Long teamB;
    private int matchOvers;

}
