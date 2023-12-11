package com.tekion.cricket.service;

import com.tekion.cricket.entity.Players;
import com.tekion.cricket.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public   class PlayerServiceImpl implements PlayerService{
    @Autowired
    private PlayerRepository playerRepository;
    @Override
    public List<Players> getTeamPlayer(Long teamId) {

        List<Players> listOfPlayers = playerRepository.findPlayerByTeamTeamId(teamId);
         return  listOfPlayers;
    }
    @Override
    public Players save(Players player) {

        return playerRepository.save(player);
    }
    @Override
    public List<Players> getAllPlayer() {
        return playerRepository.findAll();
    }

    @Override
    public Players getPlayerById(Long Id) {
        Optional<Players> optionalPlayers = playerRepository.findById(Id);
        return optionalPlayers.orElse(null);
    }
    @Override
    public List<Players> getSpecificTeamById(Long teamId) {

        List<Players> listOfPlayers = playerRepository.findPlayerByTeamTeamId(teamId);
        return  listOfPlayers;
    }
}
