package com.aaquib.sapient.league.standings.controller;

import com.aaquib.sapient.league.standings.entity.Competition;
import com.aaquib.sapient.league.standings.service.CompetitionsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CompetitionController {

    public static Logger log = LoggerFactory.getLogger(CompetitionController.class);

    @Autowired
    CompetitionsService competitionsService;

    @GetMapping(value = "competition")
    public List<Competition> getCompetition(@RequestParam String countryId) {
        return competitionsService.execute(countryId);
    }
}
