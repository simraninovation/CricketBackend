package com.tekion.cricket.service;

import com.tekion.cricket.dto.matchrequest.MatchResultDto;
import com.tekion.cricket.dto.matchrequest.MatchScoreDto;
import com.tekion.cricket.dto.matchrequest.NewMatchDto;
import com.tekion.cricket.entity.*;
import com.tekion.cricket.repository.*;
import com.tekion.cricket.utils.ScorecardGenerator;
import com.tekion.cricket.utils.TossUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class MatchesServiceImpl implements MatchesService {
    @Autowired
    private MatchesRepository matchesRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private InningsRepository inningsRepository;

    @Autowired
    private TossRepository tossRepository;

    @Autowired
    private PlayerStatsRepository playerStatsRepository;

    @Autowired
    private BallSummaryRepository ballSummaryRepository;

    @Autowired
    private ScheduledMatchRepository scheduledMatchRepository;

    @Autowired
    private ScoreboardRepository scoreboardRepository;

    @Override
    public void save(Matches matches) {
        matchesRepository.save(matches);
    }

    public static Map<String, Object> playInnings(Team teamA, Team teamB, int oversForAMatch, Long targetScore) throws InterruptedException {
        Innings inningsDetails = new Innings();
        Map<String, Object> response = new HashMap<>();
        List<BallSummary> ballSummaries = new ArrayList<>();
        List<PlayerStats> playerStats = new ArrayList<>();

        MatchScoreDto matchScoreDto = ScorecardGenerator.battingStats(oversForAMatch, teamA, ballSummaries, playerStats, targetScore);

        ScorecardGenerator.bowlingStats(oversForAMatch, teamB, matchScoreDto, playerStats);

        inningsDetails.setWicketsFallen((long) matchScoreDto.getWicket());
        inningsDetails.setExtraRuns(0L);
        inningsDetails.setTotalScore((long) matchScoreDto.getTotalScores());
        inningsDetails.setBallingTeamId(teamB.getTeamId());
        inningsDetails.setBattingTeamId(teamA.getTeamId());
        inningsDetails.setNoOfNoBalls(0L);
        inningsDetails.setNoOfWideBalls((long) matchScoreDto.getOverTillNow());
        inningsDetails.setInnStatus(1L);
        response.put("inningsDetails", inningsDetails);
        response.put("playerStats", playerStats);
        response.put("ballSummaries", ballSummaries);
        return response;
    }

    public static Map<String, Object> matchResult(Team teamA, Team teamB, int matchOvers, Long matchId) throws InterruptedException {
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> innings = playInnings(teamA, teamB, matchOvers, matchId);
        response.put("inningsDetails", innings.get("inningsDetails"));
        response.put("overDetails", innings.get("overDetails"));
        response.put("playerStats", innings.get("playerStats"));
        response.put("ballSummaries", innings.get("ballSummaries"));
        return response;
    }

    @Override
    public MatchResultDto playTheMatch(NewMatchDto request) throws InterruptedException {
        MatchResultDto matchResultDto = new MatchResultDto();
        Long matchId = request.getMatchId();
        Long team1 = request.getTeamA();
        Long team2 = request.getTeamB();
        int matchOvers = request.getMatchOvers();
        Optional<Team> teamA = teamRepository.findById(team1);
        Optional<Team> teamB = teamRepository.findById(team2);
        log.info("{},{},{}", teamA.get().getTeamName(), " v/s ", teamB.get().getTeamName());

        Toss tossWinner = TossUtil.tossSetup(teamA.get(), teamB.get());

        Map<String, Object> inning1res, inning2res;
        log.info("------------------Inning1 has started--------------------");
        if (tossWinner.getTeamIdWonToss() == teamA.get().getTeamId()) {
            Team battingTeam = (tossWinner.getBattingTeamId() == teamA.get().getTeamId()) ? teamA.get() : teamB.get();
            Team bowlingTeam = (tossWinner.getBattingTeamId() == teamA.get().getTeamId()) ? teamB.get() : teamA.get();

            inning1res = matchResult(battingTeam, bowlingTeam, matchOvers, 0L);
            Innings inning1 = (Innings) inning1res.get("inningsDetails");
            inning2res = matchResult(bowlingTeam, battingTeam, matchOvers, inning1.getTotalScore() + 1);
        } else {
            Team battingTeam = (tossWinner.getBattingTeamId() == teamB.get().getTeamId()) ? teamB.get() : teamA.get();
            Team bowlingTeam = (tossWinner.getBattingTeamId() == teamB.get().getTeamId()) ? teamA.get() : teamB.get();

            inning1res = matchResult(battingTeam, bowlingTeam, matchOvers, 0L);
            Innings inning1 = (Innings) inning1res.get("inningsDetails");
            inning2res = matchResult(bowlingTeam, battingTeam, matchOvers, inning1.getTotalScore() + 1);
        }

        List<PlayerStats> teamAPlayerStats = (List<PlayerStats>) inning1res.get("playerStats");
        List<PlayerStats> teamBPlayerStats = (List<PlayerStats>) inning2res.get("playerStats");

        List<BallSummary> ballSummaryA = (List<BallSummary>) inning1res.get("ballSummaries");
        List<BallSummary> ballSummaryB = (List<BallSummary>) inning2res.get("ballSummaries");

        Innings firstInningsDetails = (Innings) inning1res.get("inningsDetails");
        Innings secondInningsDetails = (Innings) inning2res.get("inningsDetails");
        Matches matches = new Matches();

        Long teamAResult = firstInningsDetails.getTotalScore();
        Long teamBResult = secondInningsDetails.getTotalScore();

        String result = Optional.of(teamAResult - teamBResult)
                .filter(differences -> differences > 0)
                .map(differences -> "Team " + teamA.get().getTeamName() + " Won the match by : " + differences + " runs")
                .orElseGet(() -> teamAResult == teamBResult ? "Match Drawn!" : "Team " + teamB.get().getTeamName() + " Won the match by : " + Math.abs(10 - secondInningsDetails.getWicketsFallen()) + " wickets");

        log.info("------------------Match Summary--------------------");

        log.info("{}", result);

        StringBuilder trophy = new StringBuilder();
        trophy.append("        .-----.\n");
        trophy.append("      .'        `.\n");
        trophy.append("     /    .---.   \\\n");
        trophy.append("    |    |   |    |\n");
        trophy.append("     \\    `-'   /\n");
        trophy.append("      '--------'\n");
        trophy.append("       \\      /\n");
        trophy.append("        `----'\n");
        log.info("{}", trophy.toString());


        matchResultDto.setMatchId(matchId);
        matchResultDto.setTeamA(team1);
        matchResultDto.setTeamB(team2);
        if (teamAResult > teamBResult)
            matchResultDto.setTeamIdWhoWonMatch(team1);
        else
            matchResultDto.setTeamIdWhoWonMatch(team2);
        matchResultDto.setTeamIdWonToss(tossWinner.getTeamIdWonToss());

        matches.setTeamA(teamA.get());
        matches.setTeamB(teamB.get());
        matches.setMatchOver((long) request.getMatchOvers());
        if (teamAResult > teamBResult)
            matches.setTeamIdWhoWonMatch(team1);
        else
            matches.setTeamIdWhoWonMatch(team2);
        matches.setTeamIdWonToss(tossWinner.getTeamIdWonToss());

        Matches matchesRes = matchesRepository.save(matches);

        firstInningsDetails.setMatches(matchesRes);
        secondInningsDetails.setMatches(matchesRes);

        Innings innings1 = inningsRepository.save(firstInningsDetails);
        Innings innings2 = inningsRepository.save(secondInningsDetails);
        updateScoreboard(matchesRes, innings1, result, firstInningsDetails);
        updateScoreboard(matchesRes, innings2, result, secondInningsDetails);

        for (PlayerStats playerStat : teamAPlayerStats) {
            playerStat.setMatches(matchesRes);
        }

        for (PlayerStats playerStat : teamBPlayerStats) {
            playerStat.setMatches(matchesRes);
        }

        for (BallSummary ballSummary : ballSummaryA) {
            ballSummary.setMatches(matchesRes);
            ballSummary.setInning(innings1);
        }

        for (BallSummary ballSummary : ballSummaryB) {
            ballSummary.setMatches(matchesRes);
            ballSummary.setInning(innings2);
        }
        ScheduledMatch scheduledMatch = scheduledMatchRepository.findByTeamAAndTeamBAndAndMatchStatus(team1, team2, "Scheduled");

        scheduledMatch.setMatchStatus(result);
        scheduledMatch.setMatches(matchesRes);

        scheduledMatchRepository.save(scheduledMatch);

        ballSummaryRepository.saveAll(ballSummaryA);
        ballSummaryRepository.saveAll(ballSummaryB);

        playerStatsRepository.saveAll(teamAPlayerStats);
        playerStatsRepository.saveAll(teamBPlayerStats);

        Toss toss = tossRepository.save(tossWinner);
        return matchResultDto;
    }


    private void updateScoreboard(Matches matchesRes, Innings innings1, String result, Innings firstInningsDetails) {
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.setMatchId(matchesRes.getMatchId());
        scoreboard.setInnings(innings1);
        scoreboard.setResult(result);
        scoreboard.setTotalRuns(Math.toIntExact(firstInningsDetails.getTotalScore()));
        scoreboard.setTotalWickets(Math.toIntExact(firstInningsDetails.getWicketsFallen()));
        scoreboard.setOversBowled(Math.toIntExact(firstInningsDetails.getNoOfNoBalls()));
        scoreboardRepository.save(scoreboard);

    }

    @Override
    public Matches getMatchDetailsById(Long id){
        return matchesRepository.getById(id);
    }
}
