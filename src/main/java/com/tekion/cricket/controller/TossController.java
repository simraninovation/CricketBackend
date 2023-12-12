package com.tekion.cricket.controller;

import com.tekion.cricket.entity.Toss;
import com.tekion.cricket.service.TossService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/toss")
public class TossController {
    @Autowired
    private TossService tossService;

    @GetMapping("/all")
    public ResponseEntity<List<Toss>> getAll() {
        List<Toss> result = tossService.getAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/perform/toss/{matchId}")
    public ResponseEntity<Toss> getTossResultByMatchId(@PathVariable Long matchId ) {
        Toss result = tossService.getTossResultByMatchId(matchId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
