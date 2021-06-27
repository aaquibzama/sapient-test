package com.aaquib.sapient.league.standings.service;

import com.aaquib.sapient.league.standings.data.FootballAPIDataService;
import com.aaquib.sapient.league.standings.entity.Competition;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CompetitionsService {
    public static Logger log = LoggerFactory.getLogger(CompetitionsService.class);

    private final Map<String, List<Competition>> competitionCache = new HashMap<>();
    private final FootballAPIDataService dataService;
    private final String url;
    private final String endpoint;
    private final String apiKey;

    public CompetitionsService(
            @Autowired FootballAPIDataService dataService,
            @Value("${league.api.url}") String url,
            @Value("${league.api.endpoints.competitions}") String endpoint,
            @Value("${league.api.apiKey}") String apiKey
    ) {
        this.dataService = dataService;
        this.url = url;
        this.endpoint = endpoint;
        this.apiKey = apiKey;
    }

    public List<Competition> execute(String countryId) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("action", endpoint)
                .queryParam("APIkey", apiKey)
                .queryParam("country_id", countryId);
        ObjectMapper mapper = new ObjectMapper();
        try {
            String result = dataService.get(builder);
            List<Competition> competitionList = mapper.readValue(result, new TypeReference<List<Competition>>() {
            });
            log.info("Retrieved list of competitions: {}", competitionList);
            competitionCache.put(countryId, competitionList);
            return competitionList;
        } catch (JsonProcessingException jsonProcessingException) {
            log.error("Unable to parse response ", jsonProcessingException);
        } catch (Exception exception) {
            log.error("Unable to retrieve response ", exception);
        }
        return competitionCache.get(countryId);
    }
}
