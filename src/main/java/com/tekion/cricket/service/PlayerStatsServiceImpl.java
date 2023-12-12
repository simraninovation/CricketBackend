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
        List<PlayerStats> playerStats = playerStatsRepository.findPlayerStatsByMatches_MatchId(matchId);
        return playerStats;
    }
    @Override
    public List<PlayerStats> getPlayerStatsByPlayerId(Long playerId){
        List<PlayerStats> playerStats = playerStatsRepository.getPlayerStatsByPlayerStatsId(playerId);
        return playerStats;
    }
}
