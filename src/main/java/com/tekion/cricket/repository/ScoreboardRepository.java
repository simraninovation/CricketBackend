package com.tekion.cricket.repository;

import com.tekion.cricket.entity.Matches;
import com.tekion.cricket.entity.Scoreboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreboardRepository extends JpaRepository<Scoreboard, Long> {

    List<Scoreboard> findScoreboardByMatchId(Long id);
}
