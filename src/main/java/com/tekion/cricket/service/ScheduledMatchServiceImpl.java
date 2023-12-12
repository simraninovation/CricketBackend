package com.tekion.cricket.service;

import com.tekion.cricket.dto.scheduledmatch.ScheduledMatchDto;
import com.tekion.cricket.entity.ScheduledMatch;
import com.tekion.cricket.entity.Team;
import com.tekion.cricket.repository.ScheduledMatchRepository;
import com.tekion.cricket.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ScheduledMatchServiceImpl implements ScheduledMatchService {
    @Autowired
    private ScheduledMatchRepository scheduledMatchRepository;
    @Autowired
    TeamRepository teamRepository;
    @Override
    public ScheduledMatch scheduleMatch(ScheduledMatchDto scheduledMatchDto) {
        Team teamA = teamRepository.getById(scheduledMatchDto.getTeamAId());
        Team teamB = teamRepository.getById(scheduledMatchDto.getTeamBId());
        ScheduledMatch scheduledMatch = new ScheduledMatch();
        if (teamA.getTeamId() != null && teamB.getTeamId() != null) {
            scheduledMatch.setMatchStatus("Pending");
            scheduledMatch.setDate(new Date());
            scheduledMatch.setTeamA(scheduledMatchDto.getTeamAId());
            scheduledMatch.setTeamB(scheduledMatchDto.getTeamBId());
            scheduledMatch.setVenue("XYZ");
        }
        if (scheduledMatch.getTeamA() != null)
            return scheduledMatchRepository.save(scheduledMatch);

        return null;

    }
    @Override
    public List<ScheduledMatch> getAll() {
        return scheduledMatchRepository.findAll();
    }
    @Override
    public List<ScheduledMatch> getScheduledMatchesByTeamId(Long teamId) {
        return scheduledMatchRepository.findByTeamAOrTeamB(teamId, teamId);
    }
}