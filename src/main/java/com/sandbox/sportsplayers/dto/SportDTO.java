package com.sandbox.sportsplayers.dto;

import com.sandbox.sportsplayers.model.Sport;
import com.sandbox.sportsplayers.model.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class SportDTO {
    private String name;
    private Set<String> playerEmails;

    public SportDTO(Sport sport) {
        this.name = sport.getName();
        this.playerEmails = sport.getPlayers().stream()
                .map(Player::getEmail)
                .collect(Collectors.toSet());
    }

    public SportDTO(String sportName) {
        this.name = sportName;
        this.playerEmails = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getPlayerEmails() {
        return playerEmails;
    }

    public void setPlayerEmails(Set<String> playerEmails) {
        this.playerEmails = playerEmails;
    }
}
