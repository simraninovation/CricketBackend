package com.tekion.cricket.service;

import com.tekion.cricket.dto.scheduledmatch.ScheduledMatchDto;
import com.tekion.cricket.entity.ScheduledMatch;

import java.util.List;

public interface ScheduledMatchService {

    ScheduledMatch scheduleMatch(ScheduledMatchDto scheduledMatchDto);

    List<ScheduledMatch> getAll();

    List<ScheduledMatch> getScheduledMatchesByTeamId(Long teamId);
}
