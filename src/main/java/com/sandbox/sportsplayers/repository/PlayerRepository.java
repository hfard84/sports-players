package com.sandbox.sportsplayers.repository;

import com.sandbox.sportsplayers.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, String> {

    Player findByEmail(String email);

    List<Player> findByGenderAndLevelAndAge(Player.Gender gender, int level, int age);

    @Query("SELECT p FROM Player p WHERE p.sports IS EMPTY")
    List<Player> findPlayersWithNoSports();

    @Query("SELECT p FROM Player p JOIN p.sports s WHERE s.name IN :sports")
    List<Player> findPlayersBySportsName(List<String> sports);

//    @Query("SELECT p FROM Player p JOIN p.sports s WHERE s.name IN :sports OFFSET :offset ROWS FETCH NEXT :pageSize ROWS ONLY")
//    List<Player> findBySportsNameInWithPagination(List<String> sports, int offset, int pageSize);

//    @Query(value = "SELECT p FROM Player p OFFSET :offset ROWS FETCH NEXT :pageSize")
//    List<Player> findAllWithPagination(int offset, int pageSize);
}

// Equivalent raw SQL query equivalent to :
// SELECT * FROM players WHERE gender = 'male' AND level = 5 AND age = 25;