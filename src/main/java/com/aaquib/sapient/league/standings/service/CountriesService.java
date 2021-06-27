package com.aaquib.sapient.league.standings.service;

import com.aaquib.sapient.league.standings.data.FootballAPIDataService;
import com.aaquib.sapient.league.standings.entity.Country;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountriesService {
    public static Logger log = LoggerFactory.getLogger(CountriesService.class);

    private static List<Country> countryCache = new ArrayList<>();

    private FootballAPIDataService dataService;
    private String url;
    private String endpoint;
    private String apiKey;

    public CountriesService(
            @Autowired FootballAPIDataService dataService,
            @Value("${league.api.url}") String url,
            @Value("${league.api.endpoints.countries}") String endpoint,
            @Value("${league.api.apiKey}") String apiKey
    ) {
        this.dataService = dataService;
        this.url = url;
        this.endpoint = endpoint;
        this.apiKey = apiKey;
    }

    public List<Country> execute() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("action", endpoint)
                .queryParam("APIkey", apiKey);
        ObjectMapper mapper = new ObjectMapper();
        try {
            String result = dataService.get(builder);
            List<Country> countryList = mapper.readValue(result, new TypeReference<List<Country>>() {
            });
            log.info("Retrieved list of countries: {}", countryList);
            countryCache = countryList;
            return countryList;
        } catch (JsonProcessingException jsonProcessingException) {
            log.error("Unable to parse response ", jsonProcessingException);
        } catch (Exception exception) {
            log.error("Unable to retrieve response ", exception);
        }
        return countryCache;
    }

}
