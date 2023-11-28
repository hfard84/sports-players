package com.sandbox.sportsplayers.controller;

import com.sandbox.sportsplayers.dto.PlayerDTO;
import com.sandbox.sportsplayers.model.Player;
import com.sandbox.sportsplayers.service.PlayerService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService){
        this.playerService = playerService;
    }

    @GetMapping
    public Set<PlayerDTO> getAllPlayers() {
        return playerService.findAllPlayers();
    }

    @GetMapping("/no-sports")
    public Set<PlayerDTO> getPlayersWithNoSports() {
        return playerService.findPlayersWithNoSports();
    }

    @PutMapping("/{email}/update-sports")
    public Player updatePlayerSports(@PathVariable String email, @RequestParam
    Set<String> sportNames) {
        return playerService.updatePlayerSports(email, sportNames);
    }

//    @GetMapping
//    public List<Player> getPlayersBySport(@RequestParam(required = false) List<String> sports,
//                                          @RequestParam(defaultValue = "0") int page) {
//        return playerService.findPlayersBySports(sports, page);
//    }
}
