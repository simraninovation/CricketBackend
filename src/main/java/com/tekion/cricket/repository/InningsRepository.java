package com.tekion.cricket.repository;

import com.tekion.cricket.entity.Innings;
import com.tekion.cricket.entity.Matches;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InningsRepository extends JpaRepository<Innings, Long> {
    List<Innings> getInningsByMatches_MatchId(Long id);
    List<Innings> findByBallingTeamIdOrBallingTeamId(Long id1,Long id2);
}
