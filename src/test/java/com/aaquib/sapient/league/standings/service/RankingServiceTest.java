package com.aaquib.sapient.league.standings.service;

import com.aaquib.sapient.league.standings.data.FootballAPIDataService;
import com.aaquib.sapient.league.standings.entity.Competition;
import com.aaquib.sapient.league.standings.entity.Country;
import com.aaquib.sapient.league.standings.entity.Standing;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RankingServiceTest {

    private CompetitionsService competitionsService;

    private CountriesService countriesService;

    private StandingsService standingsService;

    private RankingService rankingService;

    @BeforeEach
    public void setup() {
        competitionsService = Mockito.mock(CompetitionsService.class);
        countriesService = Mockito.mock(CountriesService.class);
        standingsService = Mockito.mock(StandingsService.class);
        rankingService = new RankingService(competitionsService, countriesService, standingsService);
    }

    @AfterEach
    public void teardown() {
    }

    @Test
    public void testExecuteWithBlankInputs() throws Exception {
        Assertions.assertDoesNotThrow(() -> rankingService.execute(StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY));
    }

    @Test
    public void testExecuteWithBlankCountryName() throws Exception {
        String rank = rankingService.execute(StringUtils.EMPTY, "league_name", "team_name");
        Assertions.assertDoesNotThrow(() -> rankingService.execute("", "league_name", "team_name"));
        Assertions.assertEquals(StringUtils.EMPTY, rank);
    }

    @Test
    public void testExecuteWithInvalidCountryName() throws Exception {
        Country country = new Country();
        country.setCountryId("123");
        country.setCountryName("Country-1");
        Mockito.when(countriesService.execute()).thenReturn(List.of(country));
        String rank = rankingService.execute("country_name", "Non League Premier", "Scarborough Athletic");
        Assertions.assertEquals(StringUtils.EMPTY, rank);
    }

    @Test
    public void testExecuteWithBlankLeagueName() throws Exception {
        String rank = rankingService.execute("country_name", StringUtils.EMPTY, "team_name");
        Assertions.assertEquals(StringUtils.EMPTY, rank);
    }

    @Test
    public void testExecuteWithInvalidLeagueName() throws Exception {
        Country country = new Country();
        country.setCountryId("123");
        country.setCountryName("England");
        Mockito.when(countriesService.execute()).thenReturn(List.of(country));

        Competition competition = new Competition();
        competition.setCountryId("123");
        competition.setLeagueName("Random-1");
        competition.setLeagueId("123");
        Mockito.when(competitionsService.execute(Mockito.any(String.class))).thenReturn(List.of(competition));

        String rank = rankingService.execute("England", "league_name", "Scarborough Athletic");
        Assertions.assertEquals(StringUtils.EMPTY, rank);
    }

    @Test
    public void testExecuteWithBlankTeamName() throws Exception {
        String rank = rankingService.execute("country_name", "league_name", StringUtils.EMPTY);
        Assertions.assertEquals(StringUtils.EMPTY, rank);
    }

    @Test
    public void testExecuteWithInvalidTeamName() throws Exception {
        Country country = new Country();
        country.setCountryId("123");
        country.setCountryName("England");
        Mockito.when(countriesService.execute()).thenReturn(List.of(country));

        Competition competition = new Competition();
        competition.setCountryId("123");
        competition.setLeagueName("Non League Premier");
        competition.setLeagueId("123");
        Mockito.when(competitionsService.execute(Mockito.any(String.class))).thenReturn(List.of(competition));

        Standing standing = new Standing();
        standing.setCountryName("Country-1");
        standing.setLeagueName("League-1");
        standing.setLeagueId("123");
        standing.setTeamId("xyz");
        standing.setTeamName("Team-1");
        standing.setOverallLeaguePosition("1");
        Mockito.when(standingsService.execute(Mockito.any(String.class))).thenReturn(List.of(standing));

        String rank = rankingService.execute("England", "Non League Premier", "team_name");
        Assertions.assertEquals(StringUtils.EMPTY, rank);
    }

    @Test
    public void testExecuteWithValidInputs() throws Exception {
        Country country = new Country();
        country.setCountryId("123");
        country.setCountryName("England");
        Mockito.when(countriesService.execute()).thenReturn(List.of(country));

        Competition competition = new Competition();
        competition.setCountryId("123");
        competition.setLeagueName("Non League Premier");
        competition.setLeagueId("123");
        Mockito.when(competitionsService.execute(Mockito.any(String.class))).thenReturn(List.of(competition));

        Standing standing = new Standing();
        standing.setCountryName("England");
        standing.setLeagueName("Non League Premier");
        standing.setLeagueId("123");
        standing.setTeamId("xyz");
        standing.setTeamName("Scarborough Athletic");
        standing.setOverallLeaguePosition("12");
        Mockito.when(standingsService.execute(Mockito.any(String.class))).thenReturn(List.of(standing));

        String rank = rankingService.execute("England", "Non League Premier", "Scarborough Athletic");
        Assertions.assertEquals(standing.getOverallLeaguePosition(), rank);
    }
}