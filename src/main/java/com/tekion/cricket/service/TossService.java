package com.tekion.cricket.service;

import com.tekion.cricket.enums.TossResult;

public interface TossService {
    //String makeChoice(Long matchId, String winnerChoice);


    //boolean performToss(Long matchId, Long TeamAid, Long TeamBid);

    boolean performToss(Long matchId, Long TeamAid, long TeamBid);
}
