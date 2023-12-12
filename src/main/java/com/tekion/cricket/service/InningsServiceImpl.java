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
        List<Innings> innings = inningsRepository.getInningsByMatches_MatchId(matchId);
        return innings;
    }
    @Override
    public List<Innings> getAllInningsByTeamId(Long teamId){
        List<Innings> innings = inningsRepository.findByBallingTeamIdOrBallingTeamId(teamId,teamId);
        return  innings;
    }

}
