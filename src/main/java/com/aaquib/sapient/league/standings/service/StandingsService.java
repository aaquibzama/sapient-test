package com.aaquib.sapient.league.standings.service;

import com.aaquib.sapient.league.standings.data.FootballAPIDataService;
import com.aaquib.sapient.league.standings.entity.Standing;
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
public class StandingsService {

    public static Logger log = LoggerFactory.getLogger(CompetitionsService.class);

    private static Map<String, List<Standing>> standingsCache = new HashMap<>();

    private FootballAPIDataService dataService;
    private String url;
    private String endpoint;
    private String apiKey;

    public StandingsService(
            @Autowired FootballAPIDataService dataService,
            @Value("${league.api.url}") String url,
            @Value("${league.api.endpoints.standings}") String endpoint,
            @Value("${league.api.apiKey}") String apiKey
    ) {
        this.dataService = dataService;
        this.url = url;
        this.endpoint = endpoint;
        this.apiKey = apiKey;
    }

    public List<Standing> execute(String leagueId) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("action", endpoint)
                .queryParam("APIkey", apiKey)
                .queryParam("league_id", leagueId);
        ObjectMapper mapper = new ObjectMapper();
        try {
            String result = dataService.get(builder);
            List<Standing> standingsList = mapper.readValue(result, new TypeReference<List<Standing>>() {
            });
            log.info("Retrieved list of standings: {}", standingsList);
            standingsCache.put(leagueId, standingsList);
            return standingsList;
        } catch (JsonProcessingException jsonProcessingException) {
            log.error("Unable to parse response ", jsonProcessingException);
        } catch (Exception exception) {
            log.error("Unable to retrieve response ", exception);
        }
        return standingsCache.get(leagueId);
    }
}
