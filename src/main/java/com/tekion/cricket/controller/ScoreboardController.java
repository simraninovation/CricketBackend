package com.tekion.cricket.controller;

import com.tekion.cricket.entity.Scoreboard;
import com.tekion.cricket.service.ScoreboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ScoreboardController {
    @Autowired
    public ScoreboardService scoreBoardService;

    @GetMapping("/getScoreboardByMatchId/{id}")
    public ResponseEntity<List<Scoreboard>> getScoreBoardByMatchId(@PathVariable("id") Long Id){
        List<Scoreboard> scoreboard = scoreBoardService.getScoreBoardByMatchId(Id);
        return new ResponseEntity<>(scoreboard, HttpStatus.OK);
    }

}
