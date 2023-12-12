package com.tekion.cricket.controller;

import com.tekion.cricket.dto.scheduledmatch.ScheduledMatchDto;
import com.tekion.cricket.entity.ScheduledMatch;
import com.tekion.cricket.service.ScheduledMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class ScheduledMatchController {
    @Autowired
     private ScheduledMatchService scheduledMatchService;
    @PostMapping("/add")
    public ResponseEntity<ScheduledMatch> scheduleMatch(@RequestBody ScheduledMatchDto scheduledMatchDto){
        ScheduledMatch scheduledMatch = scheduledMatchService.scheduleMatch(scheduledMatchDto);
        return new ResponseEntity<>(scheduledMatch, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ScheduledMatch>> getAll(){
        List<ScheduledMatch> scheduledMatches = scheduledMatchService.getAll();
        return new ResponseEntity<>(scheduledMatches,HttpStatus.OK);
    }
    @GetMapping("/{teamId}")
    public ResponseEntity<List<ScheduledMatch>> getScheduledMatchByTeamId(Long teamId){
        List<ScheduledMatch> scheduledMatches = scheduledMatchService.getScheduledMatchesByTeamId(teamId);
        return new ResponseEntity<>(scheduledMatches,HttpStatus.OK);
    }
}
