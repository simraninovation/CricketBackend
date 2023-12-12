package com.tekion.cricket.utils;

import com.tekion.cricket.dto.matchrequest.MatchScoreDto;
import com.tekion.cricket.entity.BallSummary;
import com.tekion.cricket.entity.PlayerStats;
import com.tekion.cricket.entity.Players;
import com.tekion.cricket.entity.Team;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Random;
@Slf4j
public class ScorecardGenerator {
    private static final String[] outcomes = {"0", "1", "2", "3", "4", "5", "6", "W"};
    private static final Random random = new Random();

    public static String getRandomScore() {
        int randomIndex = random.nextInt(outcomes.length);
        return outcomes[randomIndex];
    }

    public static MatchScoreDto battingStats(int oversForAMatch, Team teamA, List<BallSummary> ballSummaries, List<PlayerStats> playerStats, Long targetScore) throws InterruptedException {
        int wicket = 0,event = 0,totalScores = 0, runs = 0, oversTillNow = 0, player = 0;
        Long extraRuns = 0L;
        String ballStats ="";

        MatchScoreDto matchSocreDto = new MatchScoreDto();

        //batting
        log.info("---------------Team {} ,{}",teamA.getTeamName()," Started the Batting");

        PlayerStats playerStatBatting = new PlayerStats();
        playerStatBatting.setPlayers(teamA.getPlayers().get(player));
        for (int over = 1; over <= oversForAMatch; over++) {
            if (wicket < 10){
                log.info("{} on the Strike",playerStatBatting.getPlayers().getFirstName());
            }
            log.info("Scores after {} over is :-", over);
            BallSummary ballDetail = new BallSummary();
            ballDetail.setCurrentOver(over);
            ballStats="";
            for (int ball = 1; ball <= 6; ball++) {
                String eve = ScorecardGenerator.getRandomScore();
                if (wicket < 10) {
                    if (eve.equals("W")) {
                        playerStatBatting.setRunScored(runs);
                        runs = 0;
                        wicket++;
                        player++;
                        ballStats += "W ";
                        playerStats.add(playerStatBatting);
                        playerStatBatting = new PlayerStats();
                        playerStatBatting.setPlayers(teamA.getPlayers().get(player));
                        log.info("--------------- RUNS AFTER WICKET TAKEN -------------------");
                        log.info("Score -> {},{},{} ", totalScores,"/",wicket);
                        log.info("--------------- NEW BATSMAN ON THE FIELD -------------------");
                        log.info("{},{}",playerStatBatting.getPlayers().getFirstName()," on the Strike");

                    } else {
                        event = Integer.parseInt(eve);
                        if (wicket <= 10) {
                            totalScores = totalScores + event;
                            runs = runs + event;
                            if(targetScore != 0L && totalScores > targetScore){
                                break;
                            }
                            ballStats += (eve + " ");
                        }
                    }
                }
                else {
                    break;
                }
                oversTillNow = over;
            }

            Thread.sleep(2000);
            log.info("Over Summary {}", ballStats);
            log.info("Score -> {},{},{}",totalScores,"/", wicket);
            ballDetail.setOutcomeOfBall(ballStats);
            ballSummaries.add(ballDetail);
            //for 2nd inning
            if(targetScore != 0L && totalScores > targetScore){
                break;
            }
            if(wicket == 10)
                break;
        }
        playerStats.add(playerStatBatting);
        matchSocreDto.setWicket(wicket);
        matchSocreDto.setOverTillNow(oversTillNow);
        matchSocreDto.setTotalScores(totalScores);
        return matchSocreDto;
    }

    public static void bowlingStats(int oversForAMatch, Team teamB, MatchScoreDto matchSocreDto, List<PlayerStats> playerStats){
        int totalWickets = 0;
        int totalOvers = 0;
        int maxWicketsAllowed = matchSocreDto.getWicket();
        int overBowledCount = 0;
        Random random = new Random();

        log.info("----Balling Summary-----");

        for (int i = 5; i < teamB.getPlayers().size(); i++) {
            Players bowler = teamB.getPlayers().get(i);
            PlayerStats playerStat = new PlayerStats();
            playerStat.setPlayers(bowler);

            int wicketsTaken = 0;
            int oversBowled = 0;
            int oversPerBowler = 0;
            oversPerBowler = (oversForAMatch > 20) ? 10 : 4;

            while (wicketsTaken < 10
                    && totalOvers < matchSocreDto.getOverTillNow() && oversBowled < oversPerBowler) {
                int wicketProbability = random.nextInt(10);
                if (wicketProbability < 4 && totalWickets < maxWicketsAllowed) {
                    wicketsTaken++;
                    totalWickets++;
                }
                oversBowled++;
                totalOvers++;
            }
            overBowledCount += oversBowled;
            if(i == teamB.getPlayers().size() - 1 && totalWickets < maxWicketsAllowed){
                wicketsTaken = maxWicketsAllowed - totalWickets;
            }
            playerStat.setWicketTaken(wicketsTaken);
            playerStat.setBallIsBowled(oversPerBowler*6);
            playerStat.setOverIsBowled(oversPerBowler);
            playerStats.add(playerStat);
            log.info("{}, {}, {}, {}",playerStat.getPlayers().getFirstName()," Took ",wicketsTaken," wickets ");

        }
    }

}
