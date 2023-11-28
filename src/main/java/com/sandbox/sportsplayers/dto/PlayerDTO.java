package com.sandbox.sportsplayers.dto;

import com.sandbox.sportsplayers.model.Player;
import com.sandbox.sportsplayers.model.Sport;

import java.util.Set;
import java.util.stream.Collectors;

public class PlayerDTO {
    private String email;
    private int level;
    private int age;
    private Player.Gender gender;
    private Set<String> sportNames;

    public PlayerDTO(Player player) {
        this.email = player.getEmail();
        this.level = player.getLevel();
        this.age = player.getAge();
        this.gender = player.getGender();
        this.sportNames = player.getSports().stream()
                .map(Sport::getName)
                .collect(Collectors.toSet());
    }

    // Getters and Setters

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Player.Gender getGender() {
        return gender;
    }

    public void setGender(Player.Gender gender) {
        this.gender = gender;
    }

    public Set<String> getSportNames() {
        return sportNames;
    }

    public void setSportNames(Set<String> sportNames) {
        this.sportNames = sportNames;
    }

}
