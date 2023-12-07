package com.tekion.cricket.service;

import com.tekion.cricket.dto.matchrequest.MatchResultDto;
import com.tekion.cricket.dto.matchrequest.NewMatchDto;
import com.tekion.cricket.entity.*;
import com.tekion.cricket.repository.MatchesRepository;
import com.tekion.cricket.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MatchesServiceImpl implements MatchesService{
    @Autowired
    private MatchesRepository matchesRepository;
    @Autowired
    private TeamRepository teamRepository;
//    @Autowired
//    private TossService tossService;


    @Override
    public void save(Matches matches) {
        matchesRepository.save(matches);
    }

    public static Map<String, Object> playInnings(Team teamA, Team teamB, int oversForAMatch, Long matchId){
        InningsDetails inningsDetails = new InningsDetails();
        List<Object> result = new ArrayList<>();
        Map<String, Object> response = new HashMap<>();
        int wicket = 0;
        int event = 0;
        int totalScores = 0;
        int runs = 0;
        int ballsPerOver = 0;
        int oversPerRun = 0;
        int ballNo = 1;
        int over = 1;
        String ballStats ="";
        List<BallSummary> ballDetailsList = new ArrayList<>();
        List<PlayerStats> playerDetails = new ArrayList<>();

        Long totalBalls = 0L;
        Long totalRuns = 0L;


        for (int overs = 1; over <= oversForAMatch; over++) {
            BallSummary ballDetail = new BallSummary();
            ballDetail.setCurrentOver(over);

            for (int ball = 1; ball <= 6; ball++) {
                totalBalls++;
                ballsPerOver++;
                List<PlayerStats> playerDetails1 = new ArrayList<>();
                for (Players batsman : teamA.getPlayers()) {
                    playerDetails1.setId(batsman.getId);
                    String eve = ScoreGenerator.getRandomScore();
                    if (wicket < 10) {
                        playerDetails1.setRunScored(runs);
                        playerDetaisl1.set(runs);
                        if (eve.equals("W")) {
                            batsman.addRuns(runs);
                            ballDetails.add(-1);
                            runs = 0;
                            wicket++;
                            ballStats += "W ";
                            playerDetails1 = new PlayerDetails();
                        } else {
                            event = Integer.parseInt(eve);
                            if (wicket <= 10) {
                                totalScores = totalScores + event;
                                runs = runs + event;
                                ballDetails.add(event);
                                ballStats += (eve + " ");

                            }
                        }
                        ballDetail.setCurrentOver(over);
                        ballDetailsList.add(ballDetail);
                        System.out.println("Total ballDetail: " + ballDetail);
                        ballNo++;
                    }
                    else {
                        break;
                    }

                }
            }
            ballDetailsList.setoutcomeOfBall(ballStats);
            if (ballsPerOver == 6) {
                oversPerRun++;
                ballsPerOver = 0;
            }
            over++;
        }

    }

    public static Map<String, Object> matchResult(Team teamA, Team teamB, int matchOvers, Long matchId) {
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> innings = playInnings(teamA, teamB, overs, matchId);
        response.put("inningsDetails", innings.get("inningsDetails"));
        response.put("overDetails", innings.get("overDetails"));
        return response;
    }


    @Override
    public MatchResultDto playTheMatch(NewMatchDto request){
        MatchResultDto matchResultDto = new MatchResultDto();
        Long matchId = matchResultDto.getMatchId();
        Long team1 = matchResultDto.getTeamA();
        Long team2 = matchResultDto.getTeamB();
        int matchOvers = matchResultDto.getMatchOvers();
        Optional<Team> teamA = teamRepository.findById(team1);
        Optional<Team> teamB = teamRepository.findById(team2);
        Map<String, Object> res = matchResult(teamA.get(), teamA.get(), matchOvers, 1L);
        Map<String, Object> res1 = matchResult(teamB.get(), teamB.get(), matchOvers, 2L);

    }

}
