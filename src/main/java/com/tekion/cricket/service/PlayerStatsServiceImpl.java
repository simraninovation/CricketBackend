package com.tekion.cricket.service;

import com.tekion.cricket.entity.PlayerStats;
import com.tekion.cricket.repository.PlayerStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerStatsServiceImpl implements PlayerStatsService{
    @Autowired
    private PlayerStatsRepository playerStatsRepository;

    @Override
    public List<PlayerStats> getAll(){
        return playerStatsRepository.findAll();
    }

    @Override
    public List<PlayerStats> getPlayerStatsByMatchId(Long matchId){
        return playerStatsRepository.findPlayerStatsByMatches_MatchId(matchId);
    }

    @Override
    public List<PlayerStats> getPlayerStatsByPlayerId(Long playerId){
        return playerStatsRepository.getPlayerStatsByPlayerStatsId(playerId);
    }
}
