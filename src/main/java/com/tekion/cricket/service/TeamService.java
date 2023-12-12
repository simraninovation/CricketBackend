package com.tekion.cricket.service;

import com.tekion.cricket.dto.team.TeamDto;
import com.tekion.cricket.entity.Team;

import java.util.List;

public interface TeamService {

    Team save(TeamDto team);

    List<Team> getAllTeams();

    void deleteTeam(Long teamId);
}
