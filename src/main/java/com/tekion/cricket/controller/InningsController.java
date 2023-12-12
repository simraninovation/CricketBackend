package com.tekion.cricket.controller;

import com.tekion.cricket.entity.Innings;
import com.tekion.cricket.service.InningsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/innings")
public class InningsController {
    @Autowired
    InningsService inningsService;

    @GetMapping()
    public ResponseEntity<List<Innings>> getAll(){
        List<Innings> innings = inningsService.getAll();
        return new ResponseEntity<>(innings, HttpStatus.OK);
    }
    @GetMapping("/{matchId}")
    public ResponseEntity<List<Innings>> getInningsByMatchId(@PathVariable Long matchId){
        List<Innings> innings = inningsService.getInningsByMatchIds(matchId);
        return new ResponseEntity<>(innings, HttpStatus.OK);
    }
    @GetMapping("/team/{teamId}")
    public ResponseEntity<List<Innings>> getAllInningsByTeamId(@PathVariable Long teamId){
        List<Innings> innings = inningsService.getAllInningsByTeamId(teamId);
        return new ResponseEntity<>(innings, HttpStatus.OK);
    }
}
