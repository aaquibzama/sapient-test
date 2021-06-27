package com.aaquib.sapient.league.standings.service;

import com.aaquib.sapient.league.standings.data.FootballAPIDataService;
import com.aaquib.sapient.league.standings.entity.Country;
import com.aaquib.sapient.league.standings.entity.Standing;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

class StandingsServiceTest {
    private StandingsService standingsService;

    private FootballAPIDataService dataService;

    @BeforeEach
    public void setup() {
        dataService = Mockito.mock(FootballAPIDataService.class);

        standingsService = new StandingsService(dataService, "http://test", "", "");
    }

    @AfterEach
    public void teardown() {

    }

    @Test
    public void testExecute() throws Exception {
        Standing standing = new Standing();
        standing.setCountryName("Country-1");
        standing.setLeagueName("League-1");
        standing.setLeagueId("123");
        standing.setTeamId("xyz");
        standing.setTeamName("Team-1");
        standing.setOverallLeaguePosition("1");

        Mockito.when(dataService.get(Mockito.any(UriComponentsBuilder.class))).thenReturn(
                (new ObjectMapper()).writeValueAsString(List.of(standing))
        );

        List<Standing> competitions = standingsService.execute("123");

        Assertions.assertEquals(1, competitions.size());
    }

    @Test
    public void testExecuteWithException() throws Exception {
        Standing standing = new Standing();
        standing.setCountryName("Country-1");
        standing.setLeagueName("League-1");
        standing.setLeagueId("123");
        standing.setTeamId("xyz");
        standing.setTeamName("Team-1");
        standing.setOverallLeaguePosition("1");

        Mockito.when(dataService.get(Mockito.any(UriComponentsBuilder.class)))
                .thenReturn(
                        (new ObjectMapper()).writeValueAsString(List.of(standing))
                ).thenThrow(new RuntimeException("Test"));

        List<Standing> standings = standingsService.execute("123");
        Assertions.assertEquals(1, standings.size());

        List<Standing> standings1 = standingsService.execute("123");
        Assertions.assertEquals(1, standings1.size());
    }
}