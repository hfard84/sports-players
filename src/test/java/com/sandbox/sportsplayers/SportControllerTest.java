package com.sandbox.sportsplayers;

import com.sandbox.sportsplayers.controller.SportController;
import com.sandbox.sportsplayers.dto.SportDTO;
import com.sandbox.sportsplayers.model.Sport;
import com.sandbox.sportsplayers.service.SportService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SportControllerTest {

    @Mock
    private SportService sportService;

    @InjectMocks
    private SportController sportController;

    @Test
    public void testGetSportsWithValidNames() {
        Set<String> names = new HashSet<>(Arrays.asList("football", "tennis", "basketball"));
        Set<SportDTO> expectedSports = new HashSet<>();
        expectedSports.add(new SportDTO("football"));
        expectedSports.add(new SportDTO("tennis"));

        when(sportService.findSportsByNames(names)).thenReturn(expectedSports);

        Set<SportDTO> actualSports = sportController.getSportsWithNames(names);
//        System.out.println(" === " + actualSports.stream().map(Sport::getName).toList());
//        System.out.println(" === " + expectedSports.stream().map(Sport::getName).toList());
        assertNotNull(actualSports);
        assertEquals(expectedSports, actualSports);
        verify(sportService).findSportsByNames(names);
    }

    @Test
    public void testGetSportsWithInvalidNames() {

        Set<String> names = new HashSet<>(List.of("INVALID"));

        when(sportService.findSportsByNames(names)).thenThrow(new RuntimeException());
        Set<SportDTO> actualSports = sportController.getSportsWithNames(names);
        assertTrue(actualSports.isEmpty());
        verify(sportService).findSportsByNames(names);

    }
}
