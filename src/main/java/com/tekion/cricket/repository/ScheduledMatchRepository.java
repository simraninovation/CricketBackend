package com.tekion.cricket.repository;

import com.tekion.cricket.entity.Matches;
import com.tekion.cricket.entity.ScheduledMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduledMatchRepository extends JpaRepository<ScheduledMatch, Long> {
    List<ScheduledMatch> findByTeamAOrTeamB(Long id1, Long id2);
    ScheduledMatch findByTeamAAndTeamBAndAndMatchStatus(Long id1,Long id2,String matchStatus);
}
