package com.tekion.cricket.service;

import com.tekion.cricket.entity.Team;

import java.util.List;

public interface TeamService {
    Team save(Team team);
    List<Team> getAllTeams();
    void deleteTeam(Long teamId);
}
