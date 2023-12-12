package com.tekion.cricket.service;

import com.tekion.cricket.entity.Innings;

import java.util.List;

public interface InningsService {
    List<Innings> getAll();
    List<Innings> getInningsByMatchIds(Long matchId);
    List<Innings> getAllInningsByTeamId(Long teamId);
}
