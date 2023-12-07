package com.tekion.cricket.service;

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
    public Team save(Team team) {

        return teamRepository.save(team);
    }
    @Override
    public List<Team> getAllTeams() {
        List<Team>  listOfTeams =  teamRepository.findAll();
        return listOfTeams;
    }
    @Override
    public void deleteTeam(Long teamId) {
        teamRepository.deleteById(teamId);
    }
}
