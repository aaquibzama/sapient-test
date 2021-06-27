package com.aaquib.sapient.league.standings.service;

import com.aaquib.sapient.league.standings.data.FootballAPIDataService;
import com.aaquib.sapient.league.standings.entity.Competition;
import com.aaquib.sapient.league.standings.entity.Country;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

class CountriesServiceTest {
    private CountriesService countriesService;

    private FootballAPIDataService dataService;

    @BeforeEach
    public void setup() {
        dataService = Mockito.mock(FootballAPIDataService.class);

        countriesService = new CountriesService(dataService, "http://test", "", "");
    }

    @AfterEach
    public void teardown() {

    }

    @Test
    public void testExecute() throws Exception {
        Country country = new Country();
        country.setCountryId("123");
        country.setCountryName("Country-1");

        Mockito.when(dataService.get(Mockito.any(UriComponentsBuilder.class))).thenReturn(
                (new ObjectMapper()).writeValueAsString(List.of(country))
        );

        List<Country> competitions = countriesService.execute();

        Assertions.assertEquals(1, competitions.size());
    }

    @Test
    public void testExecuteWithException() throws Exception {
        Country country = new Country();
        country.setCountryId("123");
        country.setCountryName("Country-1");

        Mockito.when(dataService.get(Mockito.any(UriComponentsBuilder.class)))
                .thenReturn(
                        (new ObjectMapper()).writeValueAsString(List.of(country))
                ).thenThrow(new RuntimeException("Test"));

        List<Country> countries = countriesService.execute();
        Assertions.assertEquals(1, countries.size());

        List<Country> countries1 = countriesService.execute();
        Assertions.assertEquals(1, countries1.size());
    }
}