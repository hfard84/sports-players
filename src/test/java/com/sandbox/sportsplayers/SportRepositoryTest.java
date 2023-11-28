package com.sandbox.sportsplayers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import com.sandbox.sportsplayers.model.Sport;
import com.sandbox.sportsplayers.repository.SportRepository;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SportRepositoryTest {

    @Autowired
    private SportRepository sportRepository;

    @Test
    public void testSportConnection() {

        Sport retrievedSport = sportRepository.findById("football").orElse(null);
        assertNotNull(retrievedSport);
    }

    @Test
    public void testFindAll() {

        List<Sport> retrievedSports = sportRepository.findAll();
        assertEquals(3, retrievedSports.size());
    }

    @Test
    public void testFindByNameIn() {
        Set<Sport> retrievedSports = sportRepository.findByNameIn(new HashSet<>(Arrays.asList("football", "basketball")));
        assertEquals(1, retrievedSports.size());
    }

    @Test
    public void testFindByName() {
        Sport retrievedSports = sportRepository.findByName("football");
        assertNotNull(retrievedSports);
        assertEquals(retrievedSports.getName(), "football");
    }

    @Test
    public void testFindSportsWithMultiplePlayers() {
        List<Sport> retrievedSports = sportRepository.findSportsWithMultiplePlayers();
        assertNotNull(retrievedSports);
        assertEquals(1, retrievedSports.size());
        assertEquals(retrievedSports.get(0).getName(), "soccer");
    }

    @Test
    public void testFindSportsWithNoPlayers() {
        List<Sport> retrievedSports = sportRepository.findSportsWithNoPlayers();
        assertNotNull(retrievedSports);
        assertEquals(1, retrievedSports.size());
        assertEquals(retrievedSports.get(0).getName(), "football");
    }
}
