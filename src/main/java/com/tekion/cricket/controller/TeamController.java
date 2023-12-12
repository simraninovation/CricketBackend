package com.tekion.cricket.controller;

import com.tekion.cricket.entity.Players;
import com.tekion.cricket.entity.Team;

import com.tekion.cricket.service.PlayerService;
import com.tekion.cricket.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController {
    @Autowired
    private TeamService teamService;
    @Autowired
    private PlayerService playerService;

    @PostMapping("/createTeam")
    public ResponseEntity<Team> createTeam(@RequestBody Team team) {
        Team savedTeam = teamService.save(team);
        return new ResponseEntity<>(savedTeam, HttpStatus.CREATED);
    }

    @GetMapping("/allTeams")
    public ResponseEntity<List<Team>> getAllTeams() {
        List<Team> teamsDetails = teamService.getAllTeams();
        return new ResponseEntity<>(teamsDetails, HttpStatus.OK);
    }

    @GetMapping("/specificTeamDetails/{id}")
    public ResponseEntity<List<Players>> getSpecificTeamById(@PathVariable("id") Long teamId) {
        List<Players> teamDetailSById = playerService.getSpecificTeamById(teamId);
        return new ResponseEntity<>(teamDetailSById, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long teamId) {
        teamService.deleteTeam(teamId);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }
}
