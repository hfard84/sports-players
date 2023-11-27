package com.sandbox.sportsplayers.service;


import com.sandbox.sportsplayers.model.Sport;
import com.sandbox.sportsplayers.repository.SportRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class SportService {

//    @Autowired
//    private SportRepository sportRepository;

    private final SportRepository sportRepository;

    @Autowired
    public SportService(SportRepository sportRepository) {
        this.sportRepository = sportRepository;
    }

    public List<Sport> findAllSports() {
        return sportRepository.findAll();
    }

    public Sport findSportByName(String name) {
        return sportRepository.findByName(name);
    }

    public Set<Sport> findSportsByNames(List<String> names) {
        return sportRepository.findByNameIn(names);
    }

    @Transactional
    public void deleteSport(String sportName) {
        Sport sport = sportRepository.findByName(sportName);
        sport.clearPlayers();
        sportRepository.save(sport);
        sportRepository.delete(sport);
    }
}
