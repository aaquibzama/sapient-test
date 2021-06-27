package com.aaquib.sapient.league.standings.service;

import com.aaquib.sapient.league.standings.data.FootballAPIDataService;
import com.aaquib.sapient.league.standings.entity.Competition;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

class CompetitionsServiceTest {
    private CompetitionsService competitionsService;

    private FootballAPIDataService dataService;

    @BeforeEach
    public void setup() {
        dataService = Mockito.mock(FootballAPIDataService.class);

        competitionsService = new CompetitionsService(dataService, "http://test", "", "");
    }

    @AfterEach
    public void teardown() {

    }

    @Test
    public void testExecute() throws Exception {
        Competition competition = new Competition();
        competition.setCountryId("123");
        competition.setLeagueName("Random-1");

        Mockito.when(dataService.get(Mockito.any(UriComponentsBuilder.class))).thenReturn(
                (new ObjectMapper()).writeValueAsString(List.of(competition))
        );

        List<Competition> competitions = competitionsService.execute("123");

        Assertions.assertEquals(1, competitions.size());
    }

    @Test
    public void testExecuteWithException() throws Exception {
        Competition competition = new Competition();
        competition.setCountryId("123");
        competition.setLeagueName("Random-1");

        Mockito.when(dataService.get(Mockito.any(UriComponentsBuilder.class)))
                .thenReturn(
                        (new ObjectMapper()).writeValueAsString(List.of(competition))
                ).thenThrow(new RuntimeException("Test"));

        List<Competition> competitions = competitionsService.execute("123");
        Assertions.assertEquals(1, competitions.size());

        List<Competition> competitions1 = competitionsService.execute("123");
        Assertions.assertEquals(1, competitions1.size());
    }
}