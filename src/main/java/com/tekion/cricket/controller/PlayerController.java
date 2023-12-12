package com.tekion.cricket.controller;

import com.tekion.cricket.entity.Players;
import com.tekion.cricket.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/players")
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @PostMapping("/createPlayer")
    public ResponseEntity<Players> savePlayerDetails(@RequestBody Players players){
        Players savedPlayer = playerService.save(players);
        return new ResponseEntity<>(savedPlayer, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Players> getPlayerById(@PathVariable("id") Long Id){
        Players playerId = playerService.getPlayerById(Id);
        return new ResponseEntity<>(playerId, HttpStatus.OK);
    }

    @GetMapping("/AllPlayersDetails")
    public ResponseEntity<List<Players>> getAllTeams() {
        List<Players> playersDetails = playerService.getAllPlayer();
        return new ResponseEntity<>(playersDetails, HttpStatus.OK);
    }

    @GetMapping("/{teamId}")
    @ResponseBody
    public ResponseEntity<List<Players>> getTeamPlayer(@PathVariable  Long teamId){
        List<Players> listOfPlayers = playerService.getTeamPlayer(teamId);
        return new ResponseEntity<>(listOfPlayers, HttpStatus.OK);
    }






}
