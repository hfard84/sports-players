package com.sandbox.sportsplayers.repository;

import com.sandbox.sportsplayers.model.Sport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface SportRepository extends JpaRepository<Sport, String> {

    @Query("SELECT s FROM Sport s WHERE SIZE(s.players) >= 2")
    List<Sport> findSportsWithMultiplePlayers();

    @Query("SELECT s FROM Sport s WHERE SIZE(s.players) = 0")
    List<Sport> findSportsWithNoPlayers();

    Set<Sport> findByNameIn(List<String> names);

    Sport findByName(String name);
}
