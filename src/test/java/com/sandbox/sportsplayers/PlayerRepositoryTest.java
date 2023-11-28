package com.sandbox.sportsplayers;

import com.sandbox.sportsplayers.model.Player;
import com.sandbox.sportsplayers.model.Sport;
import com.sandbox.sportsplayers.repository.PlayerRepository;
import com.sandbox.sportsplayers.repository.SportRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class PlayerRepositoryTest {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private SportRepository sportRepository;

    @Test
    public void testSportConnection() {
        Player retrievedPlayer = playerRepository.findById("player.a@players.com").orElse(null);
        assertNotNull(retrievedPlayer);
    }

    @Test
    public void testFindAll() {
        List<Player> retrievedPlayers = playerRepository.findAll();
        assertEquals(3, retrievedPlayers.size());
    }

    @Test
    public void testFindByEmail() {
        Player retrievedPlayer = playerRepository.findByEmail("player.a@players.com");
        assertNotNull(retrievedPlayer);
    }

    @Test
    public void testFindByGenderLevelAge() {
        List<Player> retrievedPlayers = playerRepository.findByGenderAndLevelAndAge(Player.Gender.male,2,32);
        assertNotNull(retrievedPlayers);
    }

    @Test
    public void testFindPlayersWithNoSports() {
        List<Player> retrievedPlayers = playerRepository.findPlayersWithNoSports();
        assertNotNull(retrievedPlayers);
        assertEquals(retrievedPlayers.get(0).getEmail(), "player.d@players.com");
    }

    @Test
    public void testFindPlayersBySportsName() {
        Set<Sport> retrievedSports = sportRepository.findByNameIn(new HashSet<>(Arrays.asList("soccer", "basketball")));
        List<Player> retrievedPlayers = playerRepository.findPlayersBySportsName(retrievedSports.stream().map(Sport::getName).collect(Collectors.toList()));
        assertNotNull(retrievedPlayers);
        assertEquals(retrievedPlayers.size(), 3);
    }
}
