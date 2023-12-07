package com.tekion.cricket.controller;

import com.tekion.cricket.service.TossService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TossController {
    @Autowired
    private TossService tossService;


//    @PostMapping( "/matches/{matchId}/make-choice")
//    public String startToss(@PathVariable Long matchId, @RequestBody String winnerChoice) {
//        return tossService.makeChoice(matchId, winnerChoice);
//    }
    @GetMapping("/perform/toss/{matchId}/{TeamAid}/{TeamBid}")
    public Boolean performToss(@PathVariable Long matchId, Long TeamAid, Long TeamBid ) {
        boolean result = tossService.performToss(matchId,TeamAid,TeamBid);

//        String response;
//        switch (result) {
//            case 0:
//                response = "Team 1 wins the toss!";
//                break;
//            case 1:
//                response = "Team 2 wins the toss!";
//                break;
//            default:
//                response = "Toss result not determined!";
//        }

        return result;
    }

}
