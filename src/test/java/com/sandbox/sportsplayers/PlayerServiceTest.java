package com.sandbox.sportsplayers;

import com.sandbox.sportsplayers.model.Player;
import com.sandbox.sportsplayers.model.Sport;
import com.sandbox.sportsplayers.repository.PlayerRepository;
import com.sandbox.sportsplayers.repository.SportRepository;
import com.sandbox.sportsplayers.service.PlayerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceTest {

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private SportRepository sportRepository;

    @InjectMocks
    private PlayerService playerService;

    @Test
    public void testUpdatePlayerSports() {
        String email = "player.a@players.com";
        Set<String> sportNames = new HashSet<>(Arrays.asList("football", "basketball"));
        Set<Sport> sports = new HashSet<>();
        sports.add(new Sport("football"));
        sports.add(new Sport("basketball"));
        Player player = new Player(email);
        player.setSports(new HashSet<>());

        when(playerRepository.findByEmail(email)).thenReturn(player);
        when(sportRepository.findByNameIn(sportNames)).thenReturn(sports);

        Player updatedPlayer = playerService.updatePlayerSports("player.a@players.com", sportNames);

        assertNotNull(updatedPlayer);
        assertEquals(sports, updatedPlayer.getSports());
        verify(playerRepository).findByEmail(email);
        verify(sportRepository).findByNameIn(sportNames);
        verify(playerRepository).save(player);
    }
}
