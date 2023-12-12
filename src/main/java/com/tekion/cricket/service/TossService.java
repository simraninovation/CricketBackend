package com.tekion.cricket.service;

import com.tekion.cricket.entity.Toss;

import java.util.List;

public interface TossService {
    List<Toss> getAll();
    Toss getTossResultByMatchId(Long matchId);
}
