package com.sandbox.sportsplayers.service;

import com.sandbox.sportsplayers.model.Player;
import com.sandbox.sportsplayers.model.Sport;
import com.sandbox.sportsplayers.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final SportRepository sportRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository, SportRepository sportRepository) {
        this.playerRepository = playerRepository;
        this.sportRepository = sportRepository;
    }

    public List<Player> findPlayersWithNoSports() {
        return playerRepository.findPlayersWithNoSports();
    }

    @Transactional
    public Player updatePlayerSports(String email, List<String> sportNames) {
        Player player = playerRepository.findByEmail(email);
        Set<Sport> sports = sportRepository.findByNameIn(sportNames);
        player.setSports(sports);
        return playerRepository.save(player);

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
