package com.sandbox.sportsplayers.controller;

import com.sandbox.sportsplayers.model.Player;
import com.sandbox.sportsplayers.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;
    @GetMapping("/no-sports")
    public List<Player> getPlayersWithNoSports() {
        return playerService.findPlayersWithNoSports();
    }

    @PutMapping("/{email}/update-sports")
    public Player updatePlayerSports(@PathVariable String email, @RequestParam
    List<String> sportNames) {
        return playerService.updatePlayerSports(email, sportNames);
    }

    @GetMapping
    public List<Player> getPlayersBySport(@RequestParam(required = false) List<String> sports,
                                          @RequestParam(defaultValue = "0") int page) {
        return playerService.findPlayersBySports(sports, page);
    }
}
