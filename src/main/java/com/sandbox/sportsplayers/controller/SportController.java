package com.sandbox.sportsplayers.controller;

import com.sandbox.sportsplayers.model.Sport;
import com.sandbox.sportsplayers.service.SportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/sports")
public class SportController {

    @Autowired
    private SportService sportService;

    @GetMapping
    public Set<Sport> getSportsWithNames(@RequestParam List<String> name)
    {
        try{
            return sportService.findSportsByNames(name);
        } catch (Exception e) {
            return new HashSet<>();
            // LOG
        }
    }

    @DeleteMapping("/{sportName}")
    public void deleteSport(@PathVariable String sportName) {
        try {
            sportService.deleteSport(sportName);
        } catch (Exception e) {
            //LOG
        } }
}
