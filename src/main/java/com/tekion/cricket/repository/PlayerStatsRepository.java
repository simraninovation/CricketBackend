package com.tekion.cricket.repository;

import com.tekion.cricket.entity.Matches;
import com.tekion.cricket.entity.PlayerStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerStatsRepository extends JpaRepository<PlayerStats, Long> {
    List<PlayerStats> findPlayerStatsByMatches_MatchId(Long id);
    List<PlayerStats> getPlayerStatsByPlayerStatsId(Long id);
}
