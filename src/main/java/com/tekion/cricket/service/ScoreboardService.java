package com.tekion.cricket.service;

import com.tekion.cricket.entity.Scoreboard;

import java.util.List;

public interface ScoreboardService {

    List<Scoreboard> getScoreBoardByMatchId(Long id);
}
