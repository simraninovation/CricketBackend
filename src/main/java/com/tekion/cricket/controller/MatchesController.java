package com.tekion.cricket.controller;

import com.tekion.cricket.dto.matchrequest.MatchResultDto;
import com.tekion.cricket.dto.matchrequest.NewMatchDto;
import com.tekion.cricket.entity.Matches;
import com.tekion.cricket.service.MatchesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/match")
public class MatchesController {
    @Autowired
    private MatchesService matchesService;
    @PostMapping("/create")
    public String  savePlayerDetails(@RequestBody Matches matches){
        matchesService.save(matches);
        return "Scheduled Match";
    }

    @PostMapping("/playTheMatch")
    public ResponseEntity<MatchResultDto> playTheMatch(@RequestBody NewMatchDto match){
        MatchResultDto matchResultDto = matchesService.playTheMatch(match);
        return new ResponseEntity<>(matchResultDto, HttpStatus.OK);
    }





}
