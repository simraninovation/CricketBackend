package com.tekion.cricket.service;

import com.tekion.cricket.entity.Toss;
import com.tekion.cricket.repository.TossRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TossServiceImpl implements TossService {

    @Autowired
    private TossRepository tossRepository;

    @Override
    public List<Toss> getAll(){
        return tossRepository.findAll();
    }

    @Override
    public Toss getTossResultByMatchId(Long matchId) {
        Toss toss = tossRepository.getTossByMatches_MatchId(matchId);
        return toss;
    }

}
