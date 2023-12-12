package com.tekion.cricket.service;

import com.tekion.cricket.entity.Innings;
import com.tekion.cricket.repository.InningsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InningsServiceImpl implements InningsService {
    @Autowired
    private InningsRepository inningsRepository;

    @Override
    public List<Innings> getAll(){
        return inningsRepository.findAll();
    }

    @Override
    public List<Innings> getInningsByMatchIds(Long matchId) {
        return inningsRepository.getInningsByMatches_MatchId(matchId);
    }

    @Override
    public List<Innings> getAllInningsByTeamId(Long teamId){
        return inningsRepository.findByBallingTeamIdOrBallingTeamId(teamId,teamId);
    }

}
