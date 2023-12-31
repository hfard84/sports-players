package com.sandbox.sportsplayers.service;

import com.sandbox.sportsplayers.dto.PlayerDTO;
import com.sandbox.sportsplayers.model.Player;
import com.sandbox.sportsplayers.model.Sport;
import com.sandbox.sportsplayers.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final SportRepository sportRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository, SportRepository sportRepository) {
        this.playerRepository = playerRepository;
        this.sportRepository = sportRepository;
    }

    public Set<PlayerDTO> findAllPlayers() {
        List<Player> players = playerRepository.findAll();
        return players.stream().map(PlayerDTO::new).collect(Collectors.toSet());
    }

    public Set<PlayerDTO> findPlayersWithNoSports() {
        List<Player> players = playerRepository.findPlayersWithNoSports();
        return players.stream().map(PlayerDTO::new).collect(Collectors.toSet());
    }

    @Transactional
    public Player updatePlayerSports(String email, Set<String> sportNames) {
        Player player = playerRepository.findByEmail(email);
        Set<Sport> sports = sportRepository.findByNameIn(sportNames);
        player.setSports(sports);
        playerRepository.save(player);
        return player;
    }

//    public List<Player> findPlayersBySports(List<String> sports, int page) {
//        int pageSize = 10;
//        int offset = page * pageSize;
//        if (sports == null || sports.isEmpty()) {
//            return playerRepository.findAllWithPagination(offset, pageSize);
//        } else {
//            return playerRepository.findBySportsNameInWithPagination(sports, offset, pageSize);
//        }
//    }

}
