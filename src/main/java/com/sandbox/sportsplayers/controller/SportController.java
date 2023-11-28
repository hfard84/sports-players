package com.sandbox.sportsplayers.controller;

import com.sandbox.sportsplayers.dto.SportDTO;
import com.sandbox.sportsplayers.service.SportService;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/sports")
public class SportController {
    private static final Logger LOGGER = Logger.getLogger(SportController.class.getName());
    private final SportService sportService;

    public SportController(SportService sportService) {
        this.sportService = sportService;
    }

    @GetMapping("/all")
    public Set<SportDTO> getAllSports(){
        return sportService.findAllSports();
    }

    @GetMapping
    public Set<SportDTO> getSportsWithNames(@RequestParam Set<String> name)
    {
        try{
            return sportService.findSportsByNames(name);
        } catch (Exception e) {
            LOGGER.info("Invalid names were used calling GET /api/sports API exception:" + Arrays.toString(e.getStackTrace()));
            return new HashSet<>();
        }
    }

    @DeleteMapping("/{sportName}")
    public void deleteSport(@PathVariable String sportName) {
        try {
            sportService.deleteSport(sportName);
        } catch (Exception e) {
            LOGGER.info("Invalid names were used calling DELETE /api/sports API exception:" + Arrays.toString(e.getStackTrace()));
        }
    }
}
