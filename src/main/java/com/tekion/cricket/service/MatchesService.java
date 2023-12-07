package com.tekion.cricket.service;

import com.tekion.cricket.dto.matchrequest.MatchResultDto;
import com.tekion.cricket.dto.matchrequest.NewMatchDto;
import com.tekion.cricket.entity.Matches;

public interface MatchesService {
    void save(Matches matches);
    MatchResultDto playTheMatch(NewMatchDto match);

}
