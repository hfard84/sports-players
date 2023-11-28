package com.sandbox.sportsplayers.service;


import com.sandbox.sportsplayers.dto.SportDTO;
import com.sandbox.sportsplayers.model.Sport;
import com.sandbox.sportsplayers.repository.SportRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SportService {

    private final SportRepository sportRepository;

    @Autowired
    public SportService(SportRepository sportRepository) {
        this.sportRepository = sportRepository;
    }

    public Set<SportDTO> findAllSports() {
        List<Sport> sports = sportRepository.findAll();
        return sports.stream().map(SportDTO::new).collect(Collectors.toSet());
    }

    public SportDTO findSportByName(String name) {
        Sport sport = sportRepository.findByName(name);
        return new SportDTO(sport);
    }

    public Set<SportDTO> findSportsByNames(Set<String> names) {
        Set<Sport> sports = sportRepository.findByNameIn(names);
        return sports.stream().map(SportDTO::new).collect(Collectors.toSet());
    }

    @Transactional
    public void deleteSport(String sportName) {
        Sport sport = sportRepository.findByName(sportName);
        sport.clearPlayers();
        sportRepository.save(sport);
        sportRepository.delete(sport);
    }
}
