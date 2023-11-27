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
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SportRepositoryTest {

    @Autowired
    private SportRepository sportRepository;

    @Test
    public void testSportConnection() {

        Sport retrievedSport = sportRepository.findById("football").orElse(null);
        assertThat(retrievedSport).isNotNull();
    }

    @Test
    public void testFindAll() {

        List<Sport> retrievedSports = sportRepository.findAll();
        assertEquals(3, retrievedSports.size());
    }

    @Test
    public void testFindByNameIn() {
        Set<Sport> retrievedSports = sportRepository.findByNameIn(Arrays.asList("football", "basketball"));
        assertEquals(1, retrievedSports.size());
    }

    @Test
    public void testFindByName() {
        Sport retrievedSports = sportRepository.findByName("football");
        assertThat(retrievedSports).isNotNull();
        assertThat(retrievedSports.getName()).isEqualTo("football");
    }

    @Test
    public void testFindSportsWithMultiplePlayers() {
        List<Sport> retrievedSports = sportRepository.findSportsWithMultiplePlayers();
        assertThat(retrievedSports).isNotNull();
        assertEquals(1, retrievedSports.size());
        assertThat(retrievedSports.get(0).getName()).isEqualTo("soccer");
    }

    @Test
    public void testFindSportsWithNoPlayers() {
        List<Sport> retrievedSports = sportRepository.findSportsWithNoPlayers();
        assertThat(retrievedSports).isNotNull();
        assertEquals(1, retrievedSports.size());
        assertThat(retrievedSports.get(0).getName()).isEqualTo("football");
    }
}
