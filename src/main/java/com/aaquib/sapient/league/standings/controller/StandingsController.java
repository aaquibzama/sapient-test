package com.aaquib.sapient.league.standings.controller;

import com.aaquib.sapient.league.standings.entity.Standing;
import com.aaquib.sapient.league.standings.service.StandingsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StandingsController {
    public static Logger log = LoggerFactory.getLogger(StandingsController.class);

    @Autowired
    StandingsService standingsService;

    @GetMapping(value = "standings")
    public List<Standing> getStandings(@RequestParam String leagueId) {
        return standingsService.execute(leagueId);
    }
}
