package com.tekion.cricket.service;

import com.tekion.cricket.entity.Matches;
import com.tekion.cricket.entity.Toss;
import com.tekion.cricket.repository.MatchesRepository;
import com.tekion.cricket.repository.TossRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public  class TossServiceImpl implements TossService{
    @Autowired
    private TossRepository tossRepository;
    @Autowired
    private MatchesRepository matchesRepository;
    @Override
    public boolean performToss(Long matchId, Long TeamAid, long TeamBid) {
        boolean tossOutcome = new Random().nextBoolean();
        Long teamIdWonToss;
        Long battingTeamId;
        Long ballingTeamId;
        if (tossOutcome) {
            teamIdWonToss = TeamAid;
            battingTeamId = TeamAid;
            ballingTeamId = TeamBid;
        } else {
            teamIdWonToss = TeamBid;
            battingTeamId = TeamBid;
            ballingTeamId = TeamAid;
        }
        Matches matches = new Matches();
        matches.setMatchId(matchId);
        Toss toss = new Toss();
        toss.setTeamIdWonToss(teamIdWonToss);
        toss.setBattingTeamId(battingTeamId);
        toss.setBallingTeamId(ballingTeamId);
        toss.setTossOutcome(tossOutcome);
        toss.setMatches(matches);
        tossRepository.save(toss);
        return tossOutcome;


    }


}
