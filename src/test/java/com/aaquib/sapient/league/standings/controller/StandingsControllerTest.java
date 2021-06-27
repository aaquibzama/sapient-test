package com.aaquib.sapient.league.standings.controller;

import com.aaquib.sapient.league.standings.Application;
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

@SpringBootTest(classes = Application.class)
@EnableAutoConfiguration
@AutoConfigureMockMvc
public class StandingsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StandingsService standingsService;

    @Test
    public void testGetCompetition() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/standings?leagueId=1")
        ).andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(standingsService, Mockito.times(1)).execute("1");
    }
}
