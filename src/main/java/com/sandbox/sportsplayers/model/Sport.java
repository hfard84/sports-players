package com.sandbox.sportsplayers.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "sports")
public class Sport {

    public Sport() {

    }

    public Sport(String name) {
        this.name = name;
    }
    @Id
    @Column
    private String name;
    @ManyToMany
    @JoinTable(
            name = "players-sports",
            joinColumns = @JoinColumn(name = "sport_name"),
            inverseJoinColumns = @JoinColumn(name = "player_email")
    )
    private Set<Player> players;

    public void clearPlayers() {
        for (Player player : this.players) {
            player.getSports().remove(this);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
