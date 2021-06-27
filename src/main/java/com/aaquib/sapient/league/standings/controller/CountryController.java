package com.aaquib.sapient.league.standings.controller;

import com.aaquib.sapient.league.standings.entity.Country;
import com.aaquib.sapient.league.standings.service.CountriesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CountryController {

    public static Logger log = LoggerFactory.getLogger(CountryController.class);

    @Autowired
    CountriesService countriesService;

    @GetMapping(value = "countries")
    public List<Country> getCountries() {
        return countriesService.execute();
    }
}
