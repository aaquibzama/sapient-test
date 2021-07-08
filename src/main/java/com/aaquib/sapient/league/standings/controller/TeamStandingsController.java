package com.aaquib.sapient.league.standings.controller;

import com.aaquib.sapient.league.standings.entity.Standing;
import com.aaquib.sapient.league.standings.service.RankingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TeamStandingsController {

    public static Logger log = LoggerFactory.getLogger(TeamStandingsController.class);

    @Autowired
    RankingService rankingService;

    @GetMapping(value = "teamRank")
    public String getRank(@RequestParam String countryName, @RequestParam String leagueName,
                          @RequestParam String teamName) {
        return rankingService.execute(countryName, leagueName, teamName);
    }
}
