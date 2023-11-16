package com.sandbox.sportsplayers;

import com.sandbox.sportsplayers.controller.SportController;
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

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
public class SportControllerTest {

    @Mock
    private SportService sportService;

    @InjectMocks
    private SportController sportController;

    @Test
    public void testGetSportsWithValidNames() {
        List<String> names = Arrays.asList("Soccer", "Basketball");
        Set<Sport> expectedSports = new HashSet<>();
        expectedSports.add(new Sport("Soccer"));
        expectedSports.add(new Sport("Basketball"));

        Set<Sport> result = sportController.getSportsWithNames(names);

        assertEquals(expectedSports, result);
    }
}
