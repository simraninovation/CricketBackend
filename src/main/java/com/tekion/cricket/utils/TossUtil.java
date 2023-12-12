package com.tekion.cricket.utils;

import com.tekion.cricket.entity.Team;
import com.tekion.cricket.entity.Toss;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
public class TossUtil {
    public static Toss tossUtil(Team callingTeam, Team teamB) {
        String callersChoice = "";
        String teamBChoice = "";
        Team WinnerTossTeam;
        Scanner sc = new Scanner(System.in);
        log.info("Enter your choice: Head or Tail,{}", callingTeam.getTeamName());
        log.info("Enter 0 for HEAD");
        log.info("Enter 1 for TAIL");
        int choice = sc.nextInt();

        if(choice == 0) {
            callersChoice = "HEAD";
            teamBChoice = "TAIL";
        }
        else {
            callersChoice = "TAIL";
            teamBChoice = "HEAD";
        }
        int outComeOnToss = (int) (Math.random() * 2);
        if(outComeOnToss == choice)
            log.info("Toss Outcome: {}",callersChoice);
        else
            log.info("Toss Outcome: {} ", teamBChoice);

        if(outComeOnToss == choice)
            WinnerTossTeam = callingTeam;
        else
            WinnerTossTeam = teamB;

        log.info("What will you choose ?, {}",WinnerTossTeam.getTeamName());
        log.info("Enter 0 for Batting");
        log.info("Enter 1 for Bowling");
        int selectFirst = sc.nextInt();
        if(selectFirst == 0)
            log.info("Winner Team, {},{} ",WinnerTossTeam.getTeamName()," choose to: Bat");
        else
            log.info("Winner Team {},{}", WinnerTossTeam.getTeamName()," choose to: Ball");


        Toss tossWinner = new Toss();

        if(outComeOnToss == choice){
            tossWinner.setTeamIdWonToss(callingTeam.getTeamId());
            tossWinner.setTossOutcome(callersChoice);
            tossWinner.setBallingTeamId(selectFirst == 1 ? callingTeam.getTeamId() : teamB.getTeamId());
            tossWinner.setBattingTeamId(selectFirst == 0 ? callingTeam.getTeamId() : teamB.getTeamId());
        } else {
            tossWinner.setTeamIdWonToss(teamB.getTeamId());
            tossWinner.setTossOutcome(teamBChoice);
            tossWinner.setBallingTeamId(selectFirst == 1 ? teamB.getTeamId() : callingTeam.getTeamId());
            tossWinner.setBattingTeamId(selectFirst == 0 ? teamB.getTeamId() : callingTeam.getTeamId());
        }

        return  tossWinner;

    }

    public static Toss tossSetup(Team teamA, Team teamB) {
        Scanner sc = new Scanner(System.in);
        log.info("Now will play the coin for the Toss");
        log.info("Please Decide who will take the call & enter 1 for {},{},{},{} ",teamA.getTeamName()," or 2 for ",teamB.getTeamName()," whoever will take the call");
        int call = sc.nextInt();
        if (call == 1) {
            return tossUtil(teamA, teamB);
        } else {
            return tossUtil(teamB, teamA);
        }

    }
}
