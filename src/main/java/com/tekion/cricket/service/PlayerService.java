package com.tekion.cricket.service;

import com.tekion.cricket.entity.Players;

import java.util.List;

public interface PlayerService {

    List<Players> getTeamPlayer(Long teamId);

    Players save(Players player);

    List<Players> getAllPlayer();

    List<Players> getSpecificTeamById(Long teamId);

    Players getPlayerById(Long Id);
}
