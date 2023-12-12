package com.tekion.cricket.service;

import com.tekion.cricket.entity.PlayerStats;

import java.util.List;

public interface PlayerStatsService {

    List<PlayerStats> getAll();

    List<PlayerStats> getPlayerStatsByMatchId(Long matchId);

    List<PlayerStats> getPlayerStatsByPlayerId(Long matchId);
}
