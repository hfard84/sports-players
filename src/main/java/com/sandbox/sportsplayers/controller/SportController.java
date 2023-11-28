package com.sandbox.sportsplayers.controller;

import com.sandbox.sportsplayers.model.Sport;
import com.sandbox.sportsplayers.service.SportService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping
    public Set<Sport> getSportsWithNames(@RequestParam List<String> name)
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
