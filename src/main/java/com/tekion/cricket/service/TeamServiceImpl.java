package com.tekion.cricket.service;

import com.tekion.cricket.dto.team.TeamDto;
import com.tekion.cricket.entity.Team;
import com.tekion.cricket.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public Team save(TeamDto team) {
        Team teamS = new Team();
        teamS.setTeamId(team.getId());
        teamS.setTeamName(team.getTeamName());
        teamS.setCountry(team.getCountry());
        return teamRepository.save(teamS);
    }

    @Override
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @Override
    public void deleteTeam(Long teamId) {
        teamRepository.deleteById(teamId);
    }
}
