package com.tekion.cricket.controller;

import com.tekion.cricket.entity.PlayerStats;
import com.tekion.cricket.service.PlayerStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/playerStats")
public class PlayerStatsController {
    @Autowired
    private PlayerStatsService playerStatsService;

    @GetMapping("/")
    public ResponseEntity<List<PlayerStats>> getAll() {
        List<PlayerStats> result = playerStatsService.getAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/match/{matchId}")
    public ResponseEntity<List<PlayerStats>> getPlayerStatsByMatchId(@PathVariable Long matchId){
        List<PlayerStats> playerStats = playerStatsService.getPlayerStatsByMatchId(matchId);
        return new ResponseEntity<>(playerStats, HttpStatus.OK);
    }

    @GetMapping("/{playerId}")
    public ResponseEntity<List<PlayerStats>> getPlayerStatsByPlayerId(@PathVariable Long playerId){
        List<PlayerStats> playerStats = playerStatsService.getPlayerStatsByPlayerId(playerId);
        return new ResponseEntity<>(playerStats, HttpStatus.OK);
    }

}
