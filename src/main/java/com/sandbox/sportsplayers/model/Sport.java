package com.sandbox.sportsplayers.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sports")
public class Sport {
    @Id
    private String name;
    @ManyToMany
    @JoinTable(
            name = "players_sports",
            joinColumns = @JoinColumn(name = "sport_name"),
            inverseJoinColumns = @JoinColumn(name = "player_email")
    )
    private Set<Player> players;

    public Sport(String name) {
        this.name = name;
    }

    public void clearPlayers() {
        for (Player player : this.players) {
            player.getSports().remove(this);
        }
    }
}
