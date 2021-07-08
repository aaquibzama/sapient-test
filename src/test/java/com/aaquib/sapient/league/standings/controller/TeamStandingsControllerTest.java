package com.aaquib.sapient.league.standings.controller;

import com.aaquib.sapient.league.standings.Application;
import com.aaquib.sapient.league.standings.service.RankingService;
import com.aaquib.sapient.league.standings.service.StandingsService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = Application.class)
@EnableAutoConfiguration
@AutoConfigureMockMvc
class TeamStandingsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RankingService rankingService;

    @Test
    public void testGetRank() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/teamRank?countryName=England&leagueName=LeaguePremier&teamName=Scarborough")
        ).andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(rankingService, Mockito.times(1)).execute("England", "LeaguePremier", "Scarborough");
    }
}