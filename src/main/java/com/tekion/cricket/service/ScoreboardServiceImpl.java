package com.tekion.cricket.service;

import com.tekion.cricket.entity.Scoreboard;
import com.tekion.cricket.repository.ScoreboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreboardServiceImpl implements ScoreboardService{

    @Autowired
    private ScoreboardRepository scoreboardRepository;

    @Override
    public List<Scoreboard> getScoreBoardByMatchId(Long id){
        List<Scoreboard> scoreboards = scoreboardRepository.findScoreboardByMatchId(id);
        return scoreboards;
    }
}
